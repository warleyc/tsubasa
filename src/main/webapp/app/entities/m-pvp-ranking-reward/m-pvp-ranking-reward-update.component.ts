import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMPvpRankingReward, MPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';
import { MPvpRankingRewardService } from './m-pvp-ranking-reward.service';
import { IMPvpWave } from 'app/shared/model/m-pvp-wave.model';
import { MPvpWaveService } from 'app/entities/m-pvp-wave';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-update',
  templateUrl: './m-pvp-ranking-reward-update.component.html'
})
export class MPvpRankingRewardUpdateComponent implements OnInit {
  mPvpRankingReward: IMPvpRankingReward;
  isSaving: boolean;

  mpvpwaves: IMPvpWave[];

  editForm = this.fb.group({
    id: [],
    waveId: [null, [Validators.required]],
    difficulty: [null, [Validators.required]],
    rankingFrom: [null, [Validators.required]],
    rankingTo: [null, [Validators.required]],
    rewardGroupId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mPvpRankingRewardService: MPvpRankingRewardService,
    protected mPvpWaveService: MPvpWaveService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpRankingReward }) => {
      this.updateForm(mPvpRankingReward);
      this.mPvpRankingReward = mPvpRankingReward;
    });
    this.mPvpWaveService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPvpWave[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPvpWave[]>) => response.body)
      )
      .subscribe((res: IMPvpWave[]) => (this.mpvpwaves = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mPvpRankingReward: IMPvpRankingReward) {
    this.editForm.patchValue({
      id: mPvpRankingReward.id,
      waveId: mPvpRankingReward.waveId,
      difficulty: mPvpRankingReward.difficulty,
      rankingFrom: mPvpRankingReward.rankingFrom,
      rankingTo: mPvpRankingReward.rankingTo,
      rewardGroupId: mPvpRankingReward.rewardGroupId,
      idId: mPvpRankingReward.idId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpRankingReward = this.createFromForm();
    if (mPvpRankingReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpRankingRewardService.update(mPvpRankingReward));
    } else {
      this.subscribeToSaveResponse(this.mPvpRankingRewardService.create(mPvpRankingReward));
    }
  }

  private createFromForm(): IMPvpRankingReward {
    const entity = {
      ...new MPvpRankingReward(),
      id: this.editForm.get(['id']).value,
      waveId: this.editForm.get(['waveId']).value,
      difficulty: this.editForm.get(['difficulty']).value,
      rankingFrom: this.editForm.get(['rankingFrom']).value,
      rankingTo: this.editForm.get(['rankingTo']).value,
      rewardGroupId: this.editForm.get(['rewardGroupId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpRankingReward>>) {
    result.subscribe((res: HttpResponse<IMPvpRankingReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMPvpWaveById(index: number, item: IMPvpWave) {
    return item.id;
  }
}
