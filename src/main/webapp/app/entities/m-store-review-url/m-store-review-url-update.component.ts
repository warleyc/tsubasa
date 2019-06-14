import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMStoreReviewUrl, MStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';
import { MStoreReviewUrlService } from './m-store-review-url.service';

@Component({
  selector: 'jhi-m-store-review-url-update',
  templateUrl: './m-store-review-url-update.component.html'
})
export class MStoreReviewUrlUpdateComponent implements OnInit {
  mStoreReviewUrl: IMStoreReviewUrl;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    platform: [null, [Validators.required]],
    url: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mStoreReviewUrlService: MStoreReviewUrlService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mStoreReviewUrl }) => {
      this.updateForm(mStoreReviewUrl);
      this.mStoreReviewUrl = mStoreReviewUrl;
    });
  }

  updateForm(mStoreReviewUrl: IMStoreReviewUrl) {
    this.editForm.patchValue({
      id: mStoreReviewUrl.id,
      platform: mStoreReviewUrl.platform,
      url: mStoreReviewUrl.url
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mStoreReviewUrl = this.createFromForm();
    if (mStoreReviewUrl.id !== undefined) {
      this.subscribeToSaveResponse(this.mStoreReviewUrlService.update(mStoreReviewUrl));
    } else {
      this.subscribeToSaveResponse(this.mStoreReviewUrlService.create(mStoreReviewUrl));
    }
  }

  private createFromForm(): IMStoreReviewUrl {
    const entity = {
      ...new MStoreReviewUrl(),
      id: this.editForm.get(['id']).value,
      platform: this.editForm.get(['platform']).value,
      url: this.editForm.get(['url']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMStoreReviewUrl>>) {
    result.subscribe((res: HttpResponse<IMStoreReviewUrl>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
