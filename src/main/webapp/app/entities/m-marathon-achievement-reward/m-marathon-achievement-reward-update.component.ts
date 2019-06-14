import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonAchievementReward, MMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';
import { MMarathonAchievementRewardService } from './m-marathon-achievement-reward.service';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-update',
  templateUrl: './m-marathon-achievement-reward-update.component.html'
})
export class MMarathonAchievementRewardUpdateComponent implements OnInit {
  mMarathonAchievementReward: IMMarathonAchievementReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    eventPoint: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonAchievementRewardService: MMarathonAchievementRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonAchievementReward }) => {
      this.updateForm(mMarathonAchievementReward);
      this.mMarathonAchievementReward = mMarathonAchievementReward;
    });
  }

  updateForm(mMarathonAchievementReward: IMMarathonAchievementReward) {
    this.editForm.patchValue({
      id: mMarathonAchievementReward.id,
      eventId: mMarathonAchievementReward.eventId,
      eventPoint: mMarathonAchievementReward.eventPoint,
      rewardGroupId: mMarathonAchievementReward.rewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonAchievementReward = this.createFromForm();
    if (mMarathonAchievementReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonAchievementRewardService.update(mMarathonAchievementReward));
    } else {
      this.subscribeToSaveResponse(this.mMarathonAchievementRewardService.create(mMarathonAchievementReward));
    }
  }

  private createFromForm(): IMMarathonAchievementReward {
    const entity = {
      ...new MMarathonAchievementReward(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      eventPoint: this.editForm.get(['eventPoint']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonAchievementReward>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonAchievementReward>) => this.onSaveSuccess(),
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
