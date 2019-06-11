import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMChallengeQuestStageReward, MChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';
import { MChallengeQuestStageRewardService } from './m-challenge-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-challenge-quest-stage-reward-update',
  templateUrl: './m-challenge-quest-stage-reward-update.component.html'
})
export class MChallengeQuestStageRewardUpdateComponent implements OnInit {
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
    protected mChallengeQuestStageRewardService: MChallengeQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChallengeQuestStageReward }) => {
      this.updateForm(mChallengeQuestStageReward);
    });
  }

  updateForm(mChallengeQuestStageReward: IMChallengeQuestStageReward) {
    this.editForm.patchValue({
      id: mChallengeQuestStageReward.id,
      stageId: mChallengeQuestStageReward.stageId,
      exp: mChallengeQuestStageReward.exp,
      coin: mChallengeQuestStageReward.coin,
      guildPoint: mChallengeQuestStageReward.guildPoint,
      clearRewardGroupId: mChallengeQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mChallengeQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mChallengeQuestStageReward.achievementRewardGroupId,
      coopGroupId: mChallengeQuestStageReward.coopGroupId,
      specialRewardGroupId: mChallengeQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mChallengeQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mChallengeQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mChallengeQuestStageReward = this.createFromForm();
    if (mChallengeQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mChallengeQuestStageRewardService.update(mChallengeQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mChallengeQuestStageRewardService.create(mChallengeQuestStageReward));
    }
  }

  private createFromForm(): IMChallengeQuestStageReward {
    const entity = {
      ...new MChallengeQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChallengeQuestStageReward>>) {
    result.subscribe(
      (res: HttpResponse<IMChallengeQuestStageReward>) => this.onSaveSuccess(),
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
