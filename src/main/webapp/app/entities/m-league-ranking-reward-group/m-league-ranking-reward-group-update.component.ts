import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLeagueRankingRewardGroup, MLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';
import { MLeagueRankingRewardGroupService } from './m-league-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-league-ranking-reward-group-update',
  templateUrl: './m-league-ranking-reward-group-update.component.html'
})
export class MLeagueRankingRewardGroupUpdateComponent implements OnInit {
  mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mLeagueRankingRewardGroupService: MLeagueRankingRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeagueRankingRewardGroup }) => {
      this.updateForm(mLeagueRankingRewardGroup);
      this.mLeagueRankingRewardGroup = mLeagueRankingRewardGroup;
    });
  }

  updateForm(mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup) {
    this.editForm.patchValue({
      id: mLeagueRankingRewardGroup.id,
      groupId: mLeagueRankingRewardGroup.groupId,
      contentType: mLeagueRankingRewardGroup.contentType,
      contentId: mLeagueRankingRewardGroup.contentId,
      contentAmount: mLeagueRankingRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLeagueRankingRewardGroup = this.createFromForm();
    if (mLeagueRankingRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueRankingRewardGroupService.update(mLeagueRankingRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mLeagueRankingRewardGroupService.create(mLeagueRankingRewardGroup));
    }
  }

  private createFromForm(): IMLeagueRankingRewardGroup {
    const entity = {
      ...new MLeagueRankingRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeagueRankingRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMLeagueRankingRewardGroup>) => this.onSaveSuccess(),
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
