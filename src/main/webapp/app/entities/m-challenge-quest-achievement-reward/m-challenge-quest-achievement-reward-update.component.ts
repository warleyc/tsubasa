import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMChallengeQuestAchievementReward,
  MChallengeQuestAchievementReward
} from 'app/shared/model/m-challenge-quest-achievement-reward.model';
import { MChallengeQuestAchievementRewardService } from './m-challenge-quest-achievement-reward.service';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-update',
  templateUrl: './m-challenge-quest-achievement-reward-update.component.html'
})
export class MChallengeQuestAchievementRewardUpdateComponent implements OnInit {
  mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    worldId: [null, [Validators.required]],
    stageId: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]]
  });

  constructor(
    protected mChallengeQuestAchievementRewardService: MChallengeQuestAchievementRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementReward }) => {
      this.updateForm(mChallengeQuestAchievementReward);
      this.mChallengeQuestAchievementReward = mChallengeQuestAchievementReward;
    });
  }

  updateForm(mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward) {
    this.editForm.patchValue({
      id: mChallengeQuestAchievementReward.id,
      worldId: mChallengeQuestAchievementReward.worldId,
      stageId: mChallengeQuestAchievementReward.stageId,
      rewardGroupId: mChallengeQuestAchievementReward.rewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mChallengeQuestAchievementReward = this.createFromForm();
    if (mChallengeQuestAchievementReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mChallengeQuestAchievementRewardService.update(mChallengeQuestAchievementReward));
    } else {
      this.subscribeToSaveResponse(this.mChallengeQuestAchievementRewardService.create(mChallengeQuestAchievementReward));
    }
  }

  private createFromForm(): IMChallengeQuestAchievementReward {
    const entity = {
      ...new MChallengeQuestAchievementReward(),
      id: this.editForm.get(['id']).value,
      worldId: this.editForm.get(['worldId']).value,
      stageId: this.editForm.get(['stageId']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChallengeQuestAchievementReward>>) {
    result.subscribe(
      (res: HttpResponse<IMChallengeQuestAchievementReward>) => this.onSaveSuccess(),
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
