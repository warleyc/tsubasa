import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTimeattackRankingReward, MTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';
import { MTimeattackRankingRewardService } from './m-timeattack-ranking-reward.service';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-update',
  templateUrl: './m-timeattack-ranking-reward-update.component.html'
})
export class MTimeattackRankingRewardUpdateComponent implements OnInit {
  mTimeattackRankingReward: IMTimeattackRankingReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    rankingFrom: [null, [Validators.required]],
    rankingTo: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]]
  });

  constructor(
    protected mTimeattackRankingRewardService: MTimeattackRankingRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTimeattackRankingReward }) => {
      this.updateForm(mTimeattackRankingReward);
      this.mTimeattackRankingReward = mTimeattackRankingReward;
    });
  }

  updateForm(mTimeattackRankingReward: IMTimeattackRankingReward) {
    this.editForm.patchValue({
      id: mTimeattackRankingReward.id,
      eventId: mTimeattackRankingReward.eventId,
      rankingFrom: mTimeattackRankingReward.rankingFrom,
      rankingTo: mTimeattackRankingReward.rankingTo,
      rewardGroupId: mTimeattackRankingReward.rewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTimeattackRankingReward = this.createFromForm();
    if (mTimeattackRankingReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mTimeattackRankingRewardService.update(mTimeattackRankingReward));
    } else {
      this.subscribeToSaveResponse(this.mTimeattackRankingRewardService.create(mTimeattackRankingReward));
    }
  }

  private createFromForm(): IMTimeattackRankingReward {
    const entity = {
      ...new MTimeattackRankingReward(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      rankingFrom: this.editForm.get(['rankingFrom']).value,
      rankingTo: this.editForm.get(['rankingTo']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTimeattackRankingReward>>) {
    result.subscribe(
      (res: HttpResponse<IMTimeattackRankingReward>) => this.onSaveSuccess(),
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
