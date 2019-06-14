import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestStageCondition, MQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';
import { MQuestStageConditionService } from './m-quest-stage-condition.service';

@Component({
  selector: 'jhi-m-quest-stage-condition-update',
  templateUrl: './m-quest-stage-condition-update.component.html'
})
export class MQuestStageConditionUpdateComponent implements OnInit {
  mQuestStageCondition: IMQuestStageCondition;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    successConditionType: [null, [Validators.required]],
    successConditionDetailType: [],
    successConditionValue: [null, [Validators.required]],
    targetCharacterGroupId: [],
    failureConditionType: []
  });

  constructor(
    protected mQuestStageConditionService: MQuestStageConditionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestStageCondition }) => {
      this.updateForm(mQuestStageCondition);
      this.mQuestStageCondition = mQuestStageCondition;
    });
  }

  updateForm(mQuestStageCondition: IMQuestStageCondition) {
    this.editForm.patchValue({
      id: mQuestStageCondition.id,
      successConditionType: mQuestStageCondition.successConditionType,
      successConditionDetailType: mQuestStageCondition.successConditionDetailType,
      successConditionValue: mQuestStageCondition.successConditionValue,
      targetCharacterGroupId: mQuestStageCondition.targetCharacterGroupId,
      failureConditionType: mQuestStageCondition.failureConditionType
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestStageCondition = this.createFromForm();
    if (mQuestStageCondition.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestStageConditionService.update(mQuestStageCondition));
    } else {
      this.subscribeToSaveResponse(this.mQuestStageConditionService.create(mQuestStageCondition));
    }
  }

  private createFromForm(): IMQuestStageCondition {
    const entity = {
      ...new MQuestStageCondition(),
      id: this.editForm.get(['id']).value,
      successConditionType: this.editForm.get(['successConditionType']).value,
      successConditionDetailType: this.editForm.get(['successConditionDetailType']).value,
      successConditionValue: this.editForm.get(['successConditionValue']).value,
      targetCharacterGroupId: this.editForm.get(['targetCharacterGroupId']).value,
      failureConditionType: this.editForm.get(['failureConditionType']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestStageCondition>>) {
    result.subscribe((res: HttpResponse<IMQuestStageCondition>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
