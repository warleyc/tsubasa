import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestStageReward, MQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';
import { MQuestStageRewardService } from './m-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-quest-stage-reward-update',
  templateUrl: './m-quest-stage-reward-update.component.html'
})
export class MQuestStageRewardUpdateComponent implements OnInit {
  mQuestStageReward: IMQuestStageReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    stageId: [null, [Validators.required]],
    exp: [null, [Validators.required]],
    coin: [null, [Validators.required]],
    guildPoint: [null, [Validators.required]],
    clearRewardGroupId: [null, [Validators.required]],
    clearRewardWeightId: [],
    achievementRewardGroupId: [null, [Validators.required]],
    coopGroupId: [null, [Validators.required]],
    specialRewardGroupId: [],
    specialRewardAmount: [null, [Validators.required]]
  });

  constructor(
    protected mQuestStageRewardService: MQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestStageReward }) => {
      this.updateForm(mQuestStageReward);
      this.mQuestStageReward = mQuestStageReward;
    });
  }

  updateForm(mQuestStageReward: IMQuestStageReward) {
    this.editForm.patchValue({
      id: mQuestStageReward.id,
      stageId: mQuestStageReward.stageId,
      exp: mQuestStageReward.exp,
      coin: mQuestStageReward.coin,
      guildPoint: mQuestStageReward.guildPoint,
      clearRewardGroupId: mQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mQuestStageReward.achievementRewardGroupId,
      coopGroupId: mQuestStageReward.coopGroupId,
      specialRewardGroupId: mQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mQuestStageReward.specialRewardAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestStageReward = this.createFromForm();
    if (mQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestStageRewardService.update(mQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mQuestStageRewardService.create(mQuestStageReward));
    }
  }

  private createFromForm(): IMQuestStageReward {
    const entity = {
      ...new MQuestStageReward(),
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
      specialRewardAmount: this.editForm.get(['specialRewardAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestStageReward>>) {
    result.subscribe((res: HttpResponse<IMQuestStageReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
