import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCutAction, MCutAction } from 'app/shared/model/m-cut-action.model';
import { MCutActionService } from './m-cut-action.service';

@Component({
  selector: 'jhi-m-cut-action-update',
  templateUrl: './m-cut-action-update.component.html'
})
export class MCutActionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    actionCutId: [null, [Validators.required]],
    cutActionType: [null, [Validators.required]],
    cutSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCutActionService: MCutActionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCutAction }) => {
      this.updateForm(mCutAction);
    });
  }

  updateForm(mCutAction: IMCutAction) {
    this.editForm.patchValue({
      id: mCutAction.id,
      actionCutId: mCutAction.actionCutId,
      cutActionType: mCutAction.cutActionType,
      cutSequenceText: mCutAction.cutSequenceText
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
    const mCutAction = this.createFromForm();
    if (mCutAction.id !== undefined) {
      this.subscribeToSaveResponse(this.mCutActionService.update(mCutAction));
    } else {
      this.subscribeToSaveResponse(this.mCutActionService.create(mCutAction));
    }
  }

  private createFromForm(): IMCutAction {
    const entity = {
      ...new MCutAction(),
      id: this.editForm.get(['id']).value,
      actionCutId: this.editForm.get(['actionCutId']).value,
      cutActionType: this.editForm.get(['cutActionType']).value,
      cutSequenceText: this.editForm.get(['cutSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCutAction>>) {
    result.subscribe((res: HttpResponse<IMCutAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
