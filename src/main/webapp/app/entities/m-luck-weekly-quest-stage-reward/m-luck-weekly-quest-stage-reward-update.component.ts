import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLuckWeeklyQuestStageReward, MLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';
import { MLuckWeeklyQuestStageRewardService } from './m-luck-weekly-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-reward-update',
  templateUrl: './m-luck-weekly-quest-stage-reward-update.component.html'
})
export class MLuckWeeklyQuestStageRewardUpdateComponent implements OnInit {
  mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward;
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
    protected mLuckWeeklyQuestStageRewardService: MLuckWeeklyQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStageReward }) => {
      this.updateForm(mLuckWeeklyQuestStageReward);
      this.mLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageReward;
    });
  }

  updateForm(mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward) {
    this.editForm.patchValue({
      id: mLuckWeeklyQuestStageReward.id,
      stageId: mLuckWeeklyQuestStageReward.stageId,
      exp: mLuckWeeklyQuestStageReward.exp,
      coin: mLuckWeeklyQuestStageReward.coin,
      guildPoint: mLuckWeeklyQuestStageReward.guildPoint,
      clearRewardGroupId: mLuckWeeklyQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mLuckWeeklyQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mLuckWeeklyQuestStageReward.achievementRewardGroupId,
      coopGroupId: mLuckWeeklyQuestStageReward.coopGroupId,
      specialRewardGroupId: mLuckWeeklyQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mLuckWeeklyQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mLuckWeeklyQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLuckWeeklyQuestStageReward = this.createFromForm();
    if (mLuckWeeklyQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestStageRewardService.update(mLuckWeeklyQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestStageRewardService.create(mLuckWeeklyQuestStageReward));
    }
  }

  private createFromForm(): IMLuckWeeklyQuestStageReward {
    const entity = {
      ...new MLuckWeeklyQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLuckWeeklyQuestStageReward>>) {
    result.subscribe(
      (res: HttpResponse<IMLuckWeeklyQuestStageReward>) => this.onSaveSuccess(),
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
