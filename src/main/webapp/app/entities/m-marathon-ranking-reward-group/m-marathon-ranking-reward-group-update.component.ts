import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonRankingRewardGroup, MMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';
import { MMarathonRankingRewardGroupService } from './m-marathon-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-group-update',
  templateUrl: './m-marathon-ranking-reward-group-update.component.html'
})
export class MMarathonRankingRewardGroupUpdateComponent implements OnInit {
  mMarathonRankingRewardGroup: IMMarathonRankingRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonRankingRewardGroupService: MMarathonRankingRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonRankingRewardGroup }) => {
      this.updateForm(mMarathonRankingRewardGroup);
      this.mMarathonRankingRewardGroup = mMarathonRankingRewardGroup;
    });
  }

  updateForm(mMarathonRankingRewardGroup: IMMarathonRankingRewardGroup) {
    this.editForm.patchValue({
      id: mMarathonRankingRewardGroup.id,
      groupId: mMarathonRankingRewardGroup.groupId,
      contentType: mMarathonRankingRewardGroup.contentType,
      contentId: mMarathonRankingRewardGroup.contentId,
      contentAmount: mMarathonRankingRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonRankingRewardGroup = this.createFromForm();
    if (mMarathonRankingRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonRankingRewardGroupService.update(mMarathonRankingRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mMarathonRankingRewardGroupService.create(mMarathonRankingRewardGroup));
    }
  }

  private createFromForm(): IMMarathonRankingRewardGroup {
    const entity = {
      ...new MMarathonRankingRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonRankingRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonRankingRewardGroup>) => this.onSaveSuccess(),
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
