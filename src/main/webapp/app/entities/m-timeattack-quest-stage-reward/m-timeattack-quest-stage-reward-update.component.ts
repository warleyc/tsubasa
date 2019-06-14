import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTimeattackQuestStageReward, MTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';
import { MTimeattackQuestStageRewardService } from './m-timeattack-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-reward-update',
  templateUrl: './m-timeattack-quest-stage-reward-update.component.html'
})
export class MTimeattackQuestStageRewardUpdateComponent implements OnInit {
  mTimeattackQuestStageReward: IMTimeattackQuestStageReward;
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
    protected mTimeattackQuestStageRewardService: MTimeattackQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStageReward }) => {
      this.updateForm(mTimeattackQuestStageReward);
      this.mTimeattackQuestStageReward = mTimeattackQuestStageReward;
    });
  }

  updateForm(mTimeattackQuestStageReward: IMTimeattackQuestStageReward) {
    this.editForm.patchValue({
      id: mTimeattackQuestStageReward.id,
      stageId: mTimeattackQuestStageReward.stageId,
      exp: mTimeattackQuestStageReward.exp,
      coin: mTimeattackQuestStageReward.coin,
      guildPoint: mTimeattackQuestStageReward.guildPoint,
      clearRewardGroupId: mTimeattackQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mTimeattackQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mTimeattackQuestStageReward.achievementRewardGroupId,
      coopGroupId: mTimeattackQuestStageReward.coopGroupId,
      specialRewardGroupId: mTimeattackQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mTimeattackQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mTimeattackQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTimeattackQuestStageReward = this.createFromForm();
    if (mTimeattackQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mTimeattackQuestStageRewardService.update(mTimeattackQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mTimeattackQuestStageRewardService.create(mTimeattackQuestStageReward));
    }
  }

  private createFromForm(): IMTimeattackQuestStageReward {
    const entity = {
      ...new MTimeattackQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTimeattackQuestStageReward>>) {
    result.subscribe(
      (res: HttpResponse<IMTimeattackQuestStageReward>) => this.onSaveSuccess(),
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
