import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGuerillaQuestStageReward, MGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';
import { MGuerillaQuestStageRewardService } from './m-guerilla-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-reward-update',
  templateUrl: './m-guerilla-quest-stage-reward-update.component.html'
})
export class MGuerillaQuestStageRewardUpdateComponent implements OnInit {
  mGuerillaQuestStageReward: IMGuerillaQuestStageReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    stageId: [null, [Validators.required]],
    exp: [null, [Validators.required]],
    coin: [null, [Validators.required]],
    guildPoint: [null, [Validators.required]],
    clearRewardGroupId: [null, [Validators.required]],
    clearRewardWeightId: [null, [Validators.required]],
    achievementRewardGroupId: [null, [Validators.required]],
    coopGroupId: [null, [Validators.required]],
    specialRewardGroupId: [],
    specialRewardAmount: [null, [Validators.required]],
    goalRewardGroupId: []
  });

  constructor(
    protected mGuerillaQuestStageRewardService: MGuerillaQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStageReward }) => {
      this.updateForm(mGuerillaQuestStageReward);
      this.mGuerillaQuestStageReward = mGuerillaQuestStageReward;
    });
  }

  updateForm(mGuerillaQuestStageReward: IMGuerillaQuestStageReward) {
    this.editForm.patchValue({
      id: mGuerillaQuestStageReward.id,
      stageId: mGuerillaQuestStageReward.stageId,
      exp: mGuerillaQuestStageReward.exp,
      coin: mGuerillaQuestStageReward.coin,
      guildPoint: mGuerillaQuestStageReward.guildPoint,
      clearRewardGroupId: mGuerillaQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mGuerillaQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mGuerillaQuestStageReward.achievementRewardGroupId,
      coopGroupId: mGuerillaQuestStageReward.coopGroupId,
      specialRewardGroupId: mGuerillaQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mGuerillaQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mGuerillaQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGuerillaQuestStageReward = this.createFromForm();
    if (mGuerillaQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuerillaQuestStageRewardService.update(mGuerillaQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mGuerillaQuestStageRewardService.create(mGuerillaQuestStageReward));
    }
  }

  private createFromForm(): IMGuerillaQuestStageReward {
    const entity = {
      ...new MGuerillaQuestStageReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      exp: this.editForm.get(['exp']).value,
      coin: this.editForm.get(['coin']).value,
      guildPoint: this.editForm.get(['guildPoint']).value,
      clearRewardGroupId: this.editForm.get(['clearRewardGroupId']).value,
      clearRewardWeightId: this.editForm.get(['clearRewardWeightId']).value,
      achievementRewardGroupId: this.editForm.get(['achievementRewardGroupId']).value,
      coopGroupId: this.editForm.get(['coopGroupId']).value,
      specialRewardGroupId: this.editForm.get(['specialRewardGroupId']).value,
      specialRewardAmount: this.editForm.get(['specialRewardAmount']).value,
      goalRewardGroupId: this.editForm.get(['goalRewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuerillaQuestStageReward>>) {
    result.subscribe(
      (res: HttpResponse<IMGuerillaQuestStageReward>) => this.onSaveSuccess(),
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
}
