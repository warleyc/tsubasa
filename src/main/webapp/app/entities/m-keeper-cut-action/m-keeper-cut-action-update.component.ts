import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMKeeperCutAction, MKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';
import { MKeeperCutActionService } from './m-keeper-cut-action.service';

@Component({
  selector: 'jhi-m-keeper-cut-action-update',
  templateUrl: './m-keeper-cut-action-update.component.html'
})
export class MKeeperCutActionUpdateComponent implements OnInit {
  mKeeperCutAction: IMKeeperCutAction;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    actionCutId: [null, [Validators.required]],
    keeperCutActionType: [null, [Validators.required]],
    cutSequenceText: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mKeeperCutActionService: MKeeperCutActionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mKeeperCutAction }) => {
      this.updateForm(mKeeperCutAction);
      this.mKeeperCutAction = mKeeperCutAction;
    });
  }

  updateForm(mKeeperCutAction: IMKeeperCutAction) {
    this.editForm.patchValue({
      id: mKeeperCutAction.id,
      actionCutId: mKeeperCutAction.actionCutId,
      keeperCutActionType: mKeeperCutAction.keeperCutActionType,
      cutSequenceText: mKeeperCutAction.cutSequenceText
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
    const mKeeperCutAction = this.createFromForm();
    if (mKeeperCutAction.id !== undefined) {
      this.subscribeToSaveResponse(this.mKeeperCutActionService.update(mKeeperCutAction));
    } else {
      this.subscribeToSaveResponse(this.mKeeperCutActionService.create(mKeeperCutAction));
    }
  }

  private createFromForm(): IMKeeperCutAction {
    const entity = {
      ...new MKeeperCutAction(),
      id: this.editForm.get(['id']).value,
      actionCutId: this.editForm.get(['actionCutId']).value,
      keeperCutActionType: this.editForm.get(['keeperCutActionType']).value,
      cutSequenceText: this.editForm.get(['cutSequenceText']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMKeeperCutAction>>) {
    result.subscribe((res: HttpResponse<IMKeeperCutAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
