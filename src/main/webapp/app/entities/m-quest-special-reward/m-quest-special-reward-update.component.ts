import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestSpecialReward, MQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';
import { MQuestSpecialRewardService } from './m-quest-special-reward.service';

@Component({
  selector: 'jhi-m-quest-special-reward-update',
  templateUrl: './m-quest-special-reward-update.component.html'
})
export class MQuestSpecialRewardUpdateComponent implements OnInit {
  mQuestSpecialReward: IMQuestSpecialReward;
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
    protected mQuestSpecialRewardService: MQuestSpecialRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestSpecialReward }) => {
      this.updateForm(mQuestSpecialReward);
      this.mQuestSpecialReward = mQuestSpecialReward;
    });
  }

  updateForm(mQuestSpecialReward: IMQuestSpecialReward) {
    this.editForm.patchValue({
      id: mQuestSpecialReward.id,
      groupId: mQuestSpecialReward.groupId,
      weight: mQuestSpecialReward.weight,
      rank: mQuestSpecialReward.rank,
      contentType: mQuestSpecialReward.contentType,
      contentId: mQuestSpecialReward.contentId,
      contentAmount: mQuestSpecialReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestSpecialReward = this.createFromForm();
    if (mQuestSpecialReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestSpecialRewardService.update(mQuestSpecialReward));
    } else {
      this.subscribeToSaveResponse(this.mQuestSpecialRewardService.create(mQuestSpecialReward));
    }
  }

  private createFromForm(): IMQuestSpecialReward {
    const entity = {
      ...new MQuestSpecialReward(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestSpecialReward>>) {
    result.subscribe((res: HttpResponse<IMQuestSpecialReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
