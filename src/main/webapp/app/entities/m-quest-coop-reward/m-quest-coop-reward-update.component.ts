import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestCoopReward, MQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';
import { MQuestCoopRewardService } from './m-quest-coop-reward.service';

@Component({
  selector: 'jhi-m-quest-coop-reward-update',
  templateUrl: './m-quest-coop-reward-update.component.html'
})
export class MQuestCoopRewardUpdateComponent implements OnInit {
  mQuestCoopReward: IMQuestCoopReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    rank: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mQuestCoopRewardService: MQuestCoopRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestCoopReward }) => {
      this.updateForm(mQuestCoopReward);
      this.mQuestCoopReward = mQuestCoopReward;
    });
  }

  updateForm(mQuestCoopReward: IMQuestCoopReward) {
    this.editForm.patchValue({
      id: mQuestCoopReward.id,
      groupId: mQuestCoopReward.groupId,
      weight: mQuestCoopReward.weight,
      rank: mQuestCoopReward.rank,
      contentType: mQuestCoopReward.contentType,
      contentId: mQuestCoopReward.contentId,
      contentAmount: mQuestCoopReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestCoopReward = this.createFromForm();
    if (mQuestCoopReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestCoopRewardService.update(mQuestCoopReward));
    } else {
      this.subscribeToSaveResponse(this.mQuestCoopRewardService.create(mQuestCoopReward));
    }
  }

  private createFromForm(): IMQuestCoopReward {
    const entity = {
      ...new MQuestCoopReward(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      weight: this.editForm.get(['weight']).value,
      rank: this.editForm.get(['rank']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestCoopReward>>) {
    result.subscribe((res: HttpResponse<IMQuestCoopReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
