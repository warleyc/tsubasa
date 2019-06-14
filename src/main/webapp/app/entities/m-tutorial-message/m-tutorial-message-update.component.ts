import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTutorialMessage, MTutorialMessage } from 'app/shared/model/m-tutorial-message.model';
import { MTutorialMessageService } from './m-tutorial-message.service';

@Component({
  selector: 'jhi-m-tutorial-message-update',
  templateUrl: './m-tutorial-message-update.component.html'
})
export class MTutorialMessageUpdateComponent implements OnInit {
  mTutorialMessage: IMTutorialMessage;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    step: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    position: [null, [Validators.required]],
    message: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTutorialMessageService: MTutorialMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTutorialMessage }) => {
      this.updateForm(mTutorialMessage);
      this.mTutorialMessage = mTutorialMessage;
    });
  }

  updateForm(mTutorialMessage: IMTutorialMessage) {
    this.editForm.patchValue({
      id: mTutorialMessage.id,
      step: mTutorialMessage.step,
      orderNum: mTutorialMessage.orderNum,
      position: mTutorialMessage.position,
      message: mTutorialMessage.message
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
    const mTutorialMessage = this.createFromForm();
    if (mTutorialMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.mTutorialMessageService.update(mTutorialMessage));
    } else {
      this.subscribeToSaveResponse(this.mTutorialMessageService.create(mTutorialMessage));
    }
  }

  private createFromForm(): IMTutorialMessage {
    const entity = {
      ...new MTutorialMessage(),
      id: this.editForm.get(['id']).value,
      step: this.editForm.get(['step']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      position: this.editForm.get(['position']).value,
      message: this.editForm.get(['message']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTutorialMessage>>) {
    result.subscribe((res: HttpResponse<IMTutorialMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
