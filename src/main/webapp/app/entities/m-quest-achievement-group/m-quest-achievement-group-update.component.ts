import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestAchievementGroup, MQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';
import { MQuestAchievementGroupService } from './m-quest-achievement-group.service';

@Component({
  selector: 'jhi-m-quest-achievement-group-update',
  templateUrl: './m-quest-achievement-group-update.component.html'
})
export class MQuestAchievementGroupUpdateComponent implements OnInit {
  mQuestAchievementGroup: IMQuestAchievementGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    achievementType: [null, [Validators.required]],
    rank: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mQuestAchievementGroupService: MQuestAchievementGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestAchievementGroup }) => {
      this.updateForm(mQuestAchievementGroup);
      this.mQuestAchievementGroup = mQuestAchievementGroup;
    });
  }

  updateForm(mQuestAchievementGroup: IMQuestAchievementGroup) {
    this.editForm.patchValue({
      id: mQuestAchievementGroup.id,
      groupId: mQuestAchievementGroup.groupId,
      achievementType: mQuestAchievementGroup.achievementType,
      rank: mQuestAchievementGroup.rank,
      weight: mQuestAchievementGroup.weight,
      contentType: mQuestAchievementGroup.contentType,
      contentId: mQuestAchievementGroup.contentId,
      contentAmount: mQuestAchievementGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestAchievementGroup = this.createFromForm();
    if (mQuestAchievementGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestAchievementGroupService.update(mQuestAchievementGroup));
    } else {
      this.subscribeToSaveResponse(this.mQuestAchievementGroupService.create(mQuestAchievementGroup));
    }
  }

  private createFromForm(): IMQuestAchievementGroup {
    const entity = {
      ...new MQuestAchievementGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      achievementType: this.editForm.get(['achievementType']).value,
      rank: this.editForm.get(['rank']).value,
      weight: this.editForm.get(['weight']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestAchievementGroup>>) {
    result.subscribe((res: HttpResponse<IMQuestAchievementGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
