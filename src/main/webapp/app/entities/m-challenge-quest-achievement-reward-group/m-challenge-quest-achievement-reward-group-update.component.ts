import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMChallengeQuestAchievementRewardGroup,
  MChallengeQuestAchievementRewardGroup
} from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';
import { MChallengeQuestAchievementRewardGroupService } from './m-challenge-quest-achievement-reward-group.service';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-group-update',
  templateUrl: './m-challenge-quest-achievement-reward-group-update.component.html'
})
export class MChallengeQuestAchievementRewardGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mChallengeQuestAchievementRewardGroupService: MChallengeQuestAchievementRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementRewardGroup }) => {
      this.updateForm(mChallengeQuestAchievementRewardGroup);
    });
  }

  updateForm(mChallengeQuestAchievementRewardGroup: IMChallengeQuestAchievementRewardGroup) {
    this.editForm.patchValue({
      id: mChallengeQuestAchievementRewardGroup.id,
      groupId: mChallengeQuestAchievementRewardGroup.groupId,
      contentType: mChallengeQuestAchievementRewardGroup.contentType,
      contentId: mChallengeQuestAchievementRewardGroup.contentId,
      contentAmount: mChallengeQuestAchievementRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mChallengeQuestAchievementRewardGroup = this.createFromForm();
    if (mChallengeQuestAchievementRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mChallengeQuestAchievementRewardGroupService.update(mChallengeQuestAchievementRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mChallengeQuestAchievementRewardGroupService.create(mChallengeQuestAchievementRewardGroup));
    }
  }

  private createFromForm(): IMChallengeQuestAchievementRewardGroup {
    const entity = {
      ...new MChallengeQuestAchievementRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChallengeQuestAchievementRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMChallengeQuestAchievementRewardGroup>) => this.onSaveSuccess(),
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
