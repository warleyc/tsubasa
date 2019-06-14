import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMQuestStageConditionDescription,
  MQuestStageConditionDescription
} from 'app/shared/model/m-quest-stage-condition-description.model';
import { MQuestStageConditionDescriptionService } from './m-quest-stage-condition-description.service';

@Component({
  selector: 'jhi-m-quest-stage-condition-description-update',
  templateUrl: './m-quest-stage-condition-description-update.component.html'
})
export class MQuestStageConditionDescriptionUpdateComponent implements OnInit {
  mQuestStageConditionDescription: IMQuestStageConditionDescription;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    successConditionType: [null, [Validators.required]],
    successConditionDetailTypeValue: [null, [Validators.required]],
    hasExistTargetCharacterGroup: [null, [Validators.required]],
    hasSuccessConditionValueOneOnly: [null, [Validators.required]],
    failureConditionTypeValue: [null, [Validators.required]],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mQuestStageConditionDescriptionService: MQuestStageConditionDescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestStageConditionDescription }) => {
      this.updateForm(mQuestStageConditionDescription);
      this.mQuestStageConditionDescription = mQuestStageConditionDescription;
    });
  }

  updateForm(mQuestStageConditionDescription: IMQuestStageConditionDescription) {
    this.editForm.patchValue({
      id: mQuestStageConditionDescription.id,
      successConditionType: mQuestStageConditionDescription.successConditionType,
      successConditionDetailTypeValue: mQuestStageConditionDescription.successConditionDetailTypeValue,
      hasExistTargetCharacterGroup: mQuestStageConditionDescription.hasExistTargetCharacterGroup,
      hasSuccessConditionValueOneOnly: mQuestStageConditionDescription.hasSuccessConditionValueOneOnly,
      failureConditionTypeValue: mQuestStageConditionDescription.failureConditionTypeValue,
      description: mQuestStageConditionDescription.description
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
    const mQuestStageConditionDescription = this.createFromForm();
    if (mQuestStageConditionDescription.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestStageConditionDescriptionService.update(mQuestStageConditionDescription));
    } else {
      this.subscribeToSaveResponse(this.mQuestStageConditionDescriptionService.create(mQuestStageConditionDescription));
    }
  }

  private createFromForm(): IMQuestStageConditionDescription {
    const entity = {
      ...new MQuestStageConditionDescription(),
      id: this.editForm.get(['id']).value,
      successConditionType: this.editForm.get(['successConditionType']).value,
      successConditionDetailTypeValue: this.editForm.get(['successConditionDetailTypeValue']).value,
      hasExistTargetCharacterGroup: this.editForm.get(['hasExistTargetCharacterGroup']).value,
      hasSuccessConditionValueOneOnly: this.editForm.get(['hasSuccessConditionValueOneOnly']).value,
      failureConditionTypeValue: this.editForm.get(['failureConditionTypeValue']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestStageConditionDescription>>) {
    result.subscribe(
      (res: HttpResponse<IMQuestStageConditionDescription>) => this.onSaveSuccess(),
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
