import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMAdventQuestStageReward, MAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';
import { MAdventQuestStageRewardService } from './m-advent-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-advent-quest-stage-reward-update',
  templateUrl: './m-advent-quest-stage-reward-update.component.html'
})
export class MAdventQuestStageRewardUpdateComponent implements OnInit {
  mAdventQuestStageReward: IMAdventQuestStageReward;
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
    protected mAdventQuestStageRewardService: MAdventQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAdventQuestStageReward }) => {
      this.updateForm(mAdventQuestStageReward);
      this.mAdventQuestStageReward = mAdventQuestStageReward;
    });
  }

  updateForm(mAdventQuestStageReward: IMAdventQuestStageReward) {
    this.editForm.patchValue({
      id: mAdventQuestStageReward.id,
      stageId: mAdventQuestStageReward.stageId,
      exp: mAdventQuestStageReward.exp,
      coin: mAdventQuestStageReward.coin,
      guildPoint: mAdventQuestStageReward.guildPoint,
      clearRewardGroupId: mAdventQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mAdventQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mAdventQuestStageReward.achievementRewardGroupId,
      coopGroupId: mAdventQuestStageReward.coopGroupId,
      specialRewardGroupId: mAdventQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mAdventQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mAdventQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mAdventQuestStageReward = this.createFromForm();
    if (mAdventQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mAdventQuestStageRewardService.update(mAdventQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mAdventQuestStageRewardService.create(mAdventQuestStageReward));
    }
  }

  private createFromForm(): IMAdventQuestStageReward {
    const entity = {
      ...new MAdventQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAdventQuestStageReward>>) {
    result.subscribe((res: HttpResponse<IMAdventQuestStageReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
