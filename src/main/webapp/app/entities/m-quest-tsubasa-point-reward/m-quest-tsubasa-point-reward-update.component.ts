import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestTsubasaPointReward, MQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';
import { MQuestTsubasaPointRewardService } from './m-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-quest-tsubasa-point-reward-update',
  templateUrl: './m-quest-tsubasa-point-reward-update.component.html'
})
export class MQuestTsubasaPointRewardUpdateComponent implements OnInit {
  mQuestTsubasaPointReward: IMQuestTsubasaPointReward;
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
    protected mQuestTsubasaPointRewardService: MQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestTsubasaPointReward }) => {
      this.updateForm(mQuestTsubasaPointReward);
      this.mQuestTsubasaPointReward = mQuestTsubasaPointReward;
    });
  }

  updateForm(mQuestTsubasaPointReward: IMQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mQuestTsubasaPointReward.id,
      stageId: mQuestTsubasaPointReward.stageId,
      tsubasaPoint: mQuestTsubasaPointReward.tsubasaPoint,
      contentType: mQuestTsubasaPointReward.contentType,
      contentId: mQuestTsubasaPointReward.contentId,
      contentAmount: mQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestTsubasaPointReward = this.createFromForm();
    if (mQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestTsubasaPointRewardService.update(mQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mQuestTsubasaPointRewardService.create(mQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMQuestTsubasaPointReward {
    const entity = {
      ...new MQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
