import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMMarathonAchievementRewardGroup,
  MMarathonAchievementRewardGroup
} from 'app/shared/model/m-marathon-achievement-reward-group.model';
import { MMarathonAchievementRewardGroupService } from './m-marathon-achievement-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-group-update',
  templateUrl: './m-marathon-achievement-reward-group-update.component.html'
})
export class MMarathonAchievementRewardGroupUpdateComponent implements OnInit {
  mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonAchievementRewardGroupService: MMarathonAchievementRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonAchievementRewardGroup }) => {
      this.updateForm(mMarathonAchievementRewardGroup);
      this.mMarathonAchievementRewardGroup = mMarathonAchievementRewardGroup;
    });
  }

  updateForm(mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup) {
    this.editForm.patchValue({
      id: mMarathonAchievementRewardGroup.id,
      groupId: mMarathonAchievementRewardGroup.groupId,
      contentType: mMarathonAchievementRewardGroup.contentType,
      contentId: mMarathonAchievementRewardGroup.contentId,
      contentAmount: mMarathonAchievementRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonAchievementRewardGroup = this.createFromForm();
    if (mMarathonAchievementRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonAchievementRewardGroupService.update(mMarathonAchievementRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mMarathonAchievementRewardGroupService.create(mMarathonAchievementRewardGroup));
    }
  }

  private createFromForm(): IMMarathonAchievementRewardGroup {
    const entity = {
      ...new MMarathonAchievementRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonAchievementRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonAchievementRewardGroup>) => this.onSaveSuccess(),
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
