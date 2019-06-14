import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMPvpRankingRewardGroup, MPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';
import { MPvpRankingRewardGroupService } from './m-pvp-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-group-update',
  templateUrl: './m-pvp-ranking-reward-group-update.component.html'
})
export class MPvpRankingRewardGroupUpdateComponent implements OnInit {
  mPvpRankingRewardGroup: IMPvpRankingRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mPvpRankingRewardGroupService: MPvpRankingRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpRankingRewardGroup }) => {
      this.updateForm(mPvpRankingRewardGroup);
      this.mPvpRankingRewardGroup = mPvpRankingRewardGroup;
    });
  }

  updateForm(mPvpRankingRewardGroup: IMPvpRankingRewardGroup) {
    this.editForm.patchValue({
      id: mPvpRankingRewardGroup.id,
      groupId: mPvpRankingRewardGroup.groupId,
      contentType: mPvpRankingRewardGroup.contentType,
      contentId: mPvpRankingRewardGroup.contentId,
      contentAmount: mPvpRankingRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPvpRankingRewardGroup = this.createFromForm();
    if (mPvpRankingRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpRankingRewardGroupService.update(mPvpRankingRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mPvpRankingRewardGroupService.create(mPvpRankingRewardGroup));
    }
  }

  private createFromForm(): IMPvpRankingRewardGroup {
    const entity = {
      ...new MPvpRankingRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpRankingRewardGroup>>) {
    result.subscribe((res: HttpResponse<IMPvpRankingRewardGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
