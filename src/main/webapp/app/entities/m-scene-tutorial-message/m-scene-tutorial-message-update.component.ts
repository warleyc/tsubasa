import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMSceneTutorialMessage, MSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';
import { MSceneTutorialMessageService } from './m-scene-tutorial-message.service';

@Component({
  selector: 'jhi-m-scene-tutorial-message-update',
  templateUrl: './m-scene-tutorial-message-update.component.html'
})
export class MSceneTutorialMessageUpdateComponent implements OnInit {
  mSceneTutorialMessage: IMSceneTutorialMessage;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    target: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    position: [null, [Validators.required]],
    message: [null, [Validators.required]],
    assetName: [null, [Validators.required]],
    title: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mSceneTutorialMessageService: MSceneTutorialMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSceneTutorialMessage }) => {
      this.updateForm(mSceneTutorialMessage);
      this.mSceneTutorialMessage = mSceneTutorialMessage;
    });
  }

  updateForm(mSceneTutorialMessage: IMSceneTutorialMessage) {
    this.editForm.patchValue({
      id: mSceneTutorialMessage.id,
      target: mSceneTutorialMessage.target,
      orderNum: mSceneTutorialMessage.orderNum,
      position: mSceneTutorialMessage.position,
      message: mSceneTutorialMessage.message,
      assetName: mSceneTutorialMessage.assetName,
      title: mSceneTutorialMessage.title
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
    const mSceneTutorialMessage = this.createFromForm();
    if (mSceneTutorialMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.mSceneTutorialMessageService.update(mSceneTutorialMessage));
    } else {
      this.subscribeToSaveResponse(this.mSceneTutorialMessageService.create(mSceneTutorialMessage));
    }
  }

  private createFromForm(): IMSceneTutorialMessage {
    const entity = {
      ...new MSceneTutorialMessage(),
      id: this.editForm.get(['id']).value,
      target: this.editForm.get(['target']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      position: this.editForm.get(['position']).value,
      message: this.editForm.get(['message']).value,
      assetName: this.editForm.get(['assetName']).value,
      title: this.editForm.get(['title']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSceneTutorialMessage>>) {
    result.subscribe((res: HttpResponse<IMSceneTutorialMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
