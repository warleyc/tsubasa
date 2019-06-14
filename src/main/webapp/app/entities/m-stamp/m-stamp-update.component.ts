import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMStamp, MStamp } from 'app/shared/model/m-stamp.model';
import { MStampService } from './m-stamp.service';

@Component({
  selector: 'jhi-m-stamp-update',
  templateUrl: './m-stamp-update.component.html'
})
export class MStampUpdateComponent implements OnInit {
  mStamp: IMStamp;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    thumbnailAsset: [null, [Validators.required]],
    soundEvent: [],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mStampService: MStampService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mStamp }) => {
      this.updateForm(mStamp);
      this.mStamp = mStamp;
    });
  }

  updateForm(mStamp: IMStamp) {
    this.editForm.patchValue({
      id: mStamp.id,
      name: mStamp.name,
      thumbnailAsset: mStamp.thumbnailAsset,
      soundEvent: mStamp.soundEvent,
      description: mStamp.description
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
    const mStamp = this.createFromForm();
    if (mStamp.id !== undefined) {
      this.subscribeToSaveResponse(this.mStampService.update(mStamp));
    } else {
      this.subscribeToSaveResponse(this.mStampService.create(mStamp));
    }
  }

  private createFromForm(): IMStamp {
    const entity = {
      ...new MStamp(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      thumbnailAsset: this.editForm.get(['thumbnailAsset']).value,
      soundEvent: this.editForm.get(['soundEvent']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMStamp>>) {
    result.subscribe((res: HttpResponse<IMStamp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
