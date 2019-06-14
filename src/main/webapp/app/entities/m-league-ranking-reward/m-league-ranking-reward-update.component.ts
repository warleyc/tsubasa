import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLeagueRankingReward, MLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';
import { MLeagueRankingRewardService } from './m-league-ranking-reward.service';

@Component({
  selector: 'jhi-m-league-ranking-reward-update',
  templateUrl: './m-league-ranking-reward-update.component.html'
})
export class MLeagueRankingRewardUpdateComponent implements OnInit {
  mLeagueRankingReward: IMLeagueRankingReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    leagueHierarchy: [null, [Validators.required]],
    rankTo: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]],
    startAt: [],
    endAt: []
  });

  constructor(
    protected mLeagueRankingRewardService: MLeagueRankingRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeagueRankingReward }) => {
      this.updateForm(mLeagueRankingReward);
      this.mLeagueRankingReward = mLeagueRankingReward;
    });
  }

  updateForm(mLeagueRankingReward: IMLeagueRankingReward) {
    this.editForm.patchValue({
      id: mLeagueRankingReward.id,
      leagueHierarchy: mLeagueRankingReward.leagueHierarchy,
      rankTo: mLeagueRankingReward.rankTo,
      rewardGroupId: mLeagueRankingReward.rewardGroupId,
      startAt: mLeagueRankingReward.startAt,
      endAt: mLeagueRankingReward.endAt
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLeagueRankingReward = this.createFromForm();
    if (mLeagueRankingReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueRankingRewardService.update(mLeagueRankingReward));
    } else {
      this.subscribeToSaveResponse(this.mLeagueRankingRewardService.create(mLeagueRankingReward));
    }
  }

  private createFromForm(): IMLeagueRankingReward {
    const entity = {
      ...new MLeagueRankingReward(),
      id: this.editForm.get(['id']).value,
      leagueHierarchy: this.editForm.get(['leagueHierarchy']).value,
      rankTo: this.editForm.get(['rankTo']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeagueRankingReward>>) {
    result.subscribe((res: HttpResponse<IMLeagueRankingReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
