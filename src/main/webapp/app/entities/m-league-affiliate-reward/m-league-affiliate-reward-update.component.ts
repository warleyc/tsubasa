import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLeagueAffiliateReward, MLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';
import { MLeagueAffiliateRewardService } from './m-league-affiliate-reward.service';

@Component({
  selector: 'jhi-m-league-affiliate-reward-update',
  templateUrl: './m-league-affiliate-reward-update.component.html'
})
export class MLeagueAffiliateRewardUpdateComponent implements OnInit {
  mLeagueAffiliateReward: IMLeagueAffiliateReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    hierarchy: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mLeagueAffiliateRewardService: MLeagueAffiliateRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeagueAffiliateReward }) => {
      this.updateForm(mLeagueAffiliateReward);
      this.mLeagueAffiliateReward = mLeagueAffiliateReward;
    });
  }

  updateForm(mLeagueAffiliateReward: IMLeagueAffiliateReward) {
    this.editForm.patchValue({
      id: mLeagueAffiliateReward.id,
      hierarchy: mLeagueAffiliateReward.hierarchy,
      contentType: mLeagueAffiliateReward.contentType,
      contentId: mLeagueAffiliateReward.contentId,
      contentAmount: mLeagueAffiliateReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLeagueAffiliateReward = this.createFromForm();
    if (mLeagueAffiliateReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueAffiliateRewardService.update(mLeagueAffiliateReward));
    } else {
      this.subscribeToSaveResponse(this.mLeagueAffiliateRewardService.create(mLeagueAffiliateReward));
    }
  }

  private createFromForm(): IMLeagueAffiliateReward {
    const entity = {
      ...new MLeagueAffiliateReward(),
      id: this.editForm.get(['id']).value,
      hierarchy: this.editForm.get(['hierarchy']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeagueAffiliateReward>>) {
    result.subscribe((res: HttpResponse<IMLeagueAffiliateReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
