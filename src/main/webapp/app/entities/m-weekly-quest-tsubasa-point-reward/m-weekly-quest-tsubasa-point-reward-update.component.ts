import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMWeeklyQuestTsubasaPointReward,
  MWeeklyQuestTsubasaPointReward
} from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';
import { MWeeklyQuestTsubasaPointRewardService } from './m-weekly-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-weekly-quest-tsubasa-point-reward-update',
  templateUrl: './m-weekly-quest-tsubasa-point-reward-update.component.html'
})
export class MWeeklyQuestTsubasaPointRewardUpdateComponent implements OnInit {
  mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    stageId: [null, [Validators.required]],
    tsubasaPoint: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]]
  });

  constructor(
    protected mWeeklyQuestTsubasaPointRewardService: MWeeklyQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mWeeklyQuestTsubasaPointReward }) => {
      this.updateForm(mWeeklyQuestTsubasaPointReward);
      this.mWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointReward;
    });
  }

  updateForm(mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mWeeklyQuestTsubasaPointReward.id,
      stageId: mWeeklyQuestTsubasaPointReward.stageId,
      tsubasaPoint: mWeeklyQuestTsubasaPointReward.tsubasaPoint,
      contentType: mWeeklyQuestTsubasaPointReward.contentType,
      contentId: mWeeklyQuestTsubasaPointReward.contentId,
      contentAmount: mWeeklyQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mWeeklyQuestTsubasaPointReward = this.createFromForm();
    if (mWeeklyQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mWeeklyQuestTsubasaPointRewardService.update(mWeeklyQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mWeeklyQuestTsubasaPointRewardService.create(mWeeklyQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMWeeklyQuestTsubasaPointReward {
    const entity = {
      ...new MWeeklyQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMWeeklyQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMWeeklyQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
