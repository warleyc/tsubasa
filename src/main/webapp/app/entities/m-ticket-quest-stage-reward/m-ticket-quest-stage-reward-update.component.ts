import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTicketQuestStageReward, MTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';
import { MTicketQuestStageRewardService } from './m-ticket-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-ticket-quest-stage-reward-update',
  templateUrl: './m-ticket-quest-stage-reward-update.component.html'
})
export class MTicketQuestStageRewardUpdateComponent implements OnInit {
  mTicketQuestStageReward: IMTicketQuestStageReward;
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
    protected mTicketQuestStageRewardService: MTicketQuestStageRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTicketQuestStageReward }) => {
      this.updateForm(mTicketQuestStageReward);
      this.mTicketQuestStageReward = mTicketQuestStageReward;
    });
  }

  updateForm(mTicketQuestStageReward: IMTicketQuestStageReward) {
    this.editForm.patchValue({
      id: mTicketQuestStageReward.id,
      stageId: mTicketQuestStageReward.stageId,
      exp: mTicketQuestStageReward.exp,
      coin: mTicketQuestStageReward.coin,
      guildPoint: mTicketQuestStageReward.guildPoint,
      clearRewardGroupId: mTicketQuestStageReward.clearRewardGroupId,
      clearRewardWeightId: mTicketQuestStageReward.clearRewardWeightId,
      achievementRewardGroupId: mTicketQuestStageReward.achievementRewardGroupId,
      coopGroupId: mTicketQuestStageReward.coopGroupId,
      specialRewardGroupId: mTicketQuestStageReward.specialRewardGroupId,
      specialRewardAmount: mTicketQuestStageReward.specialRewardAmount,
      goalRewardGroupId: mTicketQuestStageReward.goalRewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTicketQuestStageReward = this.createFromForm();
    if (mTicketQuestStageReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mTicketQuestStageRewardService.update(mTicketQuestStageReward));
    } else {
      this.subscribeToSaveResponse(this.mTicketQuestStageRewardService.create(mTicketQuestStageReward));
    }
  }

  private createFromForm(): IMTicketQuestStageReward {
    const entity = {
      ...new MTicketQuestStageReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTicketQuestStageReward>>) {
    result.subscribe((res: HttpResponse<IMTicketQuestStageReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
