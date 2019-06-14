import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTimeattackRankingRewardGroup, MTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';
import { MTimeattackRankingRewardGroupService } from './m-timeattack-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-group-update',
  templateUrl: './m-timeattack-ranking-reward-group-update.component.html'
})
export class MTimeattackRankingRewardGroupUpdateComponent implements OnInit {
  mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mTimeattackRankingRewardGroupService: MTimeattackRankingRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTimeattackRankingRewardGroup }) => {
      this.updateForm(mTimeattackRankingRewardGroup);
      this.mTimeattackRankingRewardGroup = mTimeattackRankingRewardGroup;
    });
  }

  updateForm(mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup) {
    this.editForm.patchValue({
      id: mTimeattackRankingRewardGroup.id,
      groupId: mTimeattackRankingRewardGroup.groupId,
      contentType: mTimeattackRankingRewardGroup.contentType,
      contentId: mTimeattackRankingRewardGroup.contentId,
      contentAmount: mTimeattackRankingRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTimeattackRankingRewardGroup = this.createFromForm();
    if (mTimeattackRankingRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mTimeattackRankingRewardGroupService.update(mTimeattackRankingRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mTimeattackRankingRewardGroupService.create(mTimeattackRankingRewardGroup));
    }
  }

  private createFromForm(): IMTimeattackRankingRewardGroup {
    const entity = {
      ...new MTimeattackRankingRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTimeattackRankingRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMTimeattackRankingRewardGroup>) => this.onSaveSuccess(),
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
