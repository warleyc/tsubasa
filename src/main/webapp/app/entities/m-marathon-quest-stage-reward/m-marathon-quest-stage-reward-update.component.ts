import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonQuestStageReward, MMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';
import { MMarathonQuestStageRewardService } from './m-marathon-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-marathon-quest-stage-reward-update',
  templateUrl: './m-marathon-quest-stage-reward-update.component.html'
})
export class MMarathonQuestStageRewardUpdateComponent implements OnInit {
  mMarathonQuestStageReward: IMMarathonQuestStageReward;
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
    protected mMarathonQuestStageRewardService: MMarathonQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonQuestStageReward }) => {
      this.updateForm(mMarathonQuestStageReward);
      this.mMarathonQuestStageReward = mMarathonQuestStageReward;
    });
  }

  updateForm(mMarathonQuestStageReward: IMMarathonQuestStageReward) {
    this.editForm.patchValue({
      id: mMarathonQuestStageReward.id,
      stageId: mMarathonQuestStageReward.stageId,
      exp: mMarathonQuestStageReward.exp,
      coin: mMarathonQuestStageReward.coin,
      guildPoint: mMarathonQuestStageReward.guildPoint,
      clearRewardGroupId: mMarathonQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mMarathonQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mMarathonQuestStageReward.achievementRewardGroupId,
      coopGroupId: mMarathonQuestStageReward.coopGroupId,
      specialRewardGroupId: mMarathonQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mMarathonQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mMarathonQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonQuestStageReward = this.createFromForm();
    if (mMarathonQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonQuestStageRewardService.update(mMarathonQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mMarathonQuestStageRewardService.create(mMarathonQuestStageReward));
    }
  }

  private createFromForm(): IMMarathonQuestStageReward {
    const entity = {
      ...new MMarathonQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonQuestStageReward>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonQuestStageReward>) => this.onSaveSuccess(),
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
