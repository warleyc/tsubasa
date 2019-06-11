import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMChatSystemMessage, MChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';
import { MChatSystemMessageService } from './m-chat-system-message.service';

@Component({
  selector: 'jhi-m-chat-system-message-update',
  templateUrl: './m-chat-system-message-update.component.html'
})
export class MChatSystemMessageUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    messageType: [null, [Validators.required]],
    messageKey: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mChatSystemMessageService: MChatSystemMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChatSystemMessage }) => {
      this.updateForm(mChatSystemMessage);
    });
  }

  updateForm(mChatSystemMessage: IMChatSystemMessage) {
    this.editForm.patchValue({
      id: mChatSystemMessage.id,
      messageType: mChatSystemMessage.messageType,
      messageKey: mChatSystemMessage.messageKey
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
    const mChatSystemMessage = this.createFromForm();
    if (mChatSystemMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.mChatSystemMessageService.update(mChatSystemMessage));
    } else {
      this.subscribeToSaveResponse(this.mChatSystemMessageService.create(mChatSystemMessage));
    }
  }

  private createFromForm(): IMChatSystemMessage {
    const entity = {
      ...new MChatSystemMessage(),
      id: this.editForm.get(['id']).value,
      messageType: this.editForm.get(['messageType']).value,
      messageKey: this.editForm.get(['messageKey']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChatSystemMessage>>) {
    result.subscribe((res: HttpResponse<IMChatSystemMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
