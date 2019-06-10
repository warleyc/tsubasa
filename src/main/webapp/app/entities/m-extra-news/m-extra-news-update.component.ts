import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMExtraNews, MExtraNews } from 'app/shared/model/m-extra-news.model';
import { MExtraNewsService } from './m-extra-news.service';

@Component({
  selector: 'jhi-m-extra-news-update',
  templateUrl: './m-extra-news-update.component.html'
})
export class MExtraNewsUpdateComponent implements OnInit {
  mExtraNews: IMExtraNews;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    orderNum: [null, [Validators.required]],
    assetName: [null, [Validators.required]],
    webviewId: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mExtraNewsService: MExtraNewsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mExtraNews }) => {
      this.updateForm(mExtraNews);
      this.mExtraNews = mExtraNews;
    });
  }

  updateForm(mExtraNews: IMExtraNews) {
    this.editForm.patchValue({
      id: mExtraNews.id,
      orderNum: mExtraNews.orderNum,
      assetName: mExtraNews.assetName,
      webviewId: mExtraNews.webviewId,
      startAt: mExtraNews.startAt,
      endAt: mExtraNews.endAt
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
    const mExtraNews = this.createFromForm();
    if (mExtraNews.id !== undefined) {
      this.subscribeToSaveResponse(this.mExtraNewsService.update(mExtraNews));
    } else {
      this.subscribeToSaveResponse(this.mExtraNewsService.create(mExtraNews));
    }
  }

  private createFromForm(): IMExtraNews {
    const entity = {
      ...new MExtraNews(),
      id: this.editForm.get(['id']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      assetName: this.editForm.get(['assetName']).value,
      webviewId: this.editForm.get(['webviewId']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMExtraNews>>) {
    result.subscribe((res: HttpResponse<IMExtraNews>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
