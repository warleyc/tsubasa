import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonRankingReward, MMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';
import { MMarathonRankingRewardService } from './m-marathon-ranking-reward.service';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-update',
  templateUrl: './m-marathon-ranking-reward-update.component.html'
})
export class MMarathonRankingRewardUpdateComponent implements OnInit {
  mMarathonRankingReward: IMMarathonRankingReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    rankingFrom: [null, [Validators.required]],
    rankingTo: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonRankingRewardService: MMarathonRankingRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonRankingReward }) => {
      this.updateForm(mMarathonRankingReward);
      this.mMarathonRankingReward = mMarathonRankingReward;
    });
  }

  updateForm(mMarathonRankingReward: IMMarathonRankingReward) {
    this.editForm.patchValue({
      id: mMarathonRankingReward.id,
      eventId: mMarathonRankingReward.eventId,
      rankingFrom: mMarathonRankingReward.rankingFrom,
      rankingTo: mMarathonRankingReward.rankingTo,
      rewardGroupId: mMarathonRankingReward.rewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonRankingReward = this.createFromForm();
    if (mMarathonRankingReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonRankingRewardService.update(mMarathonRankingReward));
    } else {
      this.subscribeToSaveResponse(this.mMarathonRankingRewardService.create(mMarathonRankingReward));
    }
  }

  private createFromForm(): IMMarathonRankingReward {
    const entity = {
      ...new MMarathonRankingReward(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      rankingFrom: this.editForm.get(['rankingFrom']).value,
      rankingTo: this.editForm.get(['rankingTo']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonRankingReward>>) {
    result.subscribe((res: HttpResponse<IMMarathonRankingReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
