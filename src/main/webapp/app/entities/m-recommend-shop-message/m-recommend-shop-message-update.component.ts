import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMRecommendShopMessage, MRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';
import { MRecommendShopMessageService } from './m-recommend-shop-message.service';

@Component({
  selector: 'jhi-m-recommend-shop-message-update',
  templateUrl: './m-recommend-shop-message-update.component.html'
})
export class MRecommendShopMessageUpdateComponent implements OnInit {
  mRecommendShopMessage: IMRecommendShopMessage;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    message: [null, [Validators.required]],
    hasSalesPeriod: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mRecommendShopMessageService: MRecommendShopMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRecommendShopMessage }) => {
      this.updateForm(mRecommendShopMessage);
      this.mRecommendShopMessage = mRecommendShopMessage;
    });
  }

  updateForm(mRecommendShopMessage: IMRecommendShopMessage) {
    this.editForm.patchValue({
      id: mRecommendShopMessage.id,
      message: mRecommendShopMessage.message,
      hasSalesPeriod: mRecommendShopMessage.hasSalesPeriod
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
    const mRecommendShopMessage = this.createFromForm();
    if (mRecommendShopMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.mRecommendShopMessageService.update(mRecommendShopMessage));
    } else {
      this.subscribeToSaveResponse(this.mRecommendShopMessageService.create(mRecommendShopMessage));
    }
  }

  private createFromForm(): IMRecommendShopMessage {
    const entity = {
      ...new MRecommendShopMessage(),
      id: this.editForm.get(['id']).value,
      message: this.editForm.get(['message']).value,
      hasSalesPeriod: this.editForm.get(['hasSalesPeriod']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRecommendShopMessage>>) {
    result.subscribe((res: HttpResponse<IMRecommendShopMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
