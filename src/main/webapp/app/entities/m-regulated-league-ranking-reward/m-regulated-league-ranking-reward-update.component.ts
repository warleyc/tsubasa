import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMRegulatedLeagueRankingReward, MRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';
import { MRegulatedLeagueRankingRewardService } from './m-regulated-league-ranking-reward.service';

@Component({
  selector: 'jhi-m-regulated-league-ranking-reward-update',
  templateUrl: './m-regulated-league-ranking-reward-update.component.html'
})
export class MRegulatedLeagueRankingRewardUpdateComponent implements OnInit {
  mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    regulatedLeagueId: [null, [Validators.required]],
    leagueHierarchy: [null, [Validators.required]],
    rankTo: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]]
  });

  constructor(
    protected mRegulatedLeagueRankingRewardService: MRegulatedLeagueRankingRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mRegulatedLeagueRankingReward }) => {
      this.updateForm(mRegulatedLeagueRankingReward);
      this.mRegulatedLeagueRankingReward = mRegulatedLeagueRankingReward;
    });
  }

  updateForm(mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward) {
    this.editForm.patchValue({
      id: mRegulatedLeagueRankingReward.id,
      regulatedLeagueId: mRegulatedLeagueRankingReward.regulatedLeagueId,
      leagueHierarchy: mRegulatedLeagueRankingReward.leagueHierarchy,
      rankTo: mRegulatedLeagueRankingReward.rankTo,
      rewardGroupId: mRegulatedLeagueRankingReward.rewardGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mRegulatedLeagueRankingReward = this.createFromForm();
    if (mRegulatedLeagueRankingReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mRegulatedLeagueRankingRewardService.update(mRegulatedLeagueRankingReward));
    } else {
      this.subscribeToSaveResponse(this.mRegulatedLeagueRankingRewardService.create(mRegulatedLeagueRankingReward));
    }
  }

  private createFromForm(): IMRegulatedLeagueRankingReward {
    const entity = {
      ...new MRegulatedLeagueRankingReward(),
      id: this.editForm.get(['id']).value,
      regulatedLeagueId: this.editForm.get(['regulatedLeagueId']).value,
      leagueHierarchy: this.editForm.get(['leagueHierarchy']).value,
      rankTo: this.editForm.get(['rankTo']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMRegulatedLeagueRankingReward>>) {
    result.subscribe(
      (res: HttpResponse<IMRegulatedLeagueRankingReward>) => this.onSaveSuccess(),
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
