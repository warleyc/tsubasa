import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMRegulationMatchTutorialMessage,
  MRegulationMatchTutorialMessage
} from 'app/shared/model/m-regulation-match-tutorial-message.model';
import { MRegulationMatchTutorialMessageService } from './m-regulation-match-tutorial-message.service';

@Component({
  selector: 'jhi-m-regulation-match-tutorial-message-update',
  templateUrl: './m-regulation-match-tutorial-message-update.component.html'
})
export class MRegulationMatchTutorialMessageUpdateComponent implements OnInit {
  mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ruleId: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    position: [null, [Validators.required]],
    message: [null, [Validators.required]],
    assetName: [null, [Validators.required]],
    title: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mRegulationMatchTutorialMessageService: MRegulationMatchTutorialMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRegulationMatchTutorialMessage }) => {
      this.updateForm(mRegulationMatchTutorialMessage);
      this.mRegulationMatchTutorialMessage = mRegulationMatchTutorialMessage;
    });
  }

  updateForm(mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage) {
    this.editForm.patchValue({
      id: mRegulationMatchTutorialMessage.id,
      ruleId: mRegulationMatchTutorialMessage.ruleId,
      orderNum: mRegulationMatchTutorialMessage.orderNum,
      position: mRegulationMatchTutorialMessage.position,
      message: mRegulationMatchTutorialMessage.message,
      assetName: mRegulationMatchTutorialMessage.assetName,
      title: mRegulationMatchTutorialMessage.title
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
    const mRegulationMatchTutorialMessage = this.createFromForm();
    if (mRegulationMatchTutorialMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.mRegulationMatchTutorialMessageService.update(mRegulationMatchTutorialMessage));
    } else {
      this.subscribeToSaveResponse(this.mRegulationMatchTutorialMessageService.create(mRegulationMatchTutorialMessage));
    }
  }

  private createFromForm(): IMRegulationMatchTutorialMessage {
    const entity = {
      ...new MRegulationMatchTutorialMessage(),
      id: this.editForm.get(['id']).value,
      ruleId: this.editForm.get(['ruleId']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      position: this.editForm.get(['position']).value,
      message: this.editForm.get(['message']).value,
      assetName: this.editForm.get(['assetName']).value,
      title: this.editForm.get(['title']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRegulationMatchTutorialMessage>>) {
    result.subscribe(
      (res: HttpResponse<IMRegulationMatchTutorialMessage>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
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
