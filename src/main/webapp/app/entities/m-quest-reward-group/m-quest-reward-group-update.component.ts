import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestRewardGroup, MQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';
import { MQuestRewardGroupService } from './m-quest-reward-group.service';

@Component({
  selector: 'jhi-m-quest-reward-group-update',
  templateUrl: './m-quest-reward-group-update.component.html'
})
export class MQuestRewardGroupUpdateComponent implements OnInit {
  mQuestRewardGroup: IMQuestRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    rate: [null, [Validators.required]],
    rank: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mQuestRewardGroupService: MQuestRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestRewardGroup }) => {
      this.updateForm(mQuestRewardGroup);
      this.mQuestRewardGroup = mQuestRewardGroup;
    });
  }

  updateForm(mQuestRewardGroup: IMQuestRewardGroup) {
    this.editForm.patchValue({
      id: mQuestRewardGroup.id,
      groupId: mQuestRewardGroup.groupId,
      rate: mQuestRewardGroup.rate,
      rank: mQuestRewardGroup.rank,
      contentType: mQuestRewardGroup.contentType,
      contentId: mQuestRewardGroup.contentId,
      contentAmount: mQuestRewardGroup.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestRewardGroup = this.createFromForm();
    if (mQuestRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestRewardGroupService.update(mQuestRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mQuestRewardGroupService.create(mQuestRewardGroup));
    }
  }

  private createFromForm(): IMQuestRewardGroup {
    const entity = {
      ...new MQuestRewardGroup(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      rate: this.editForm.get(['rate']).value,
      rank: this.editForm.get(['rank']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestRewardGroup>>) {
    result.subscribe((res: HttpResponse<IMQuestRewardGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
