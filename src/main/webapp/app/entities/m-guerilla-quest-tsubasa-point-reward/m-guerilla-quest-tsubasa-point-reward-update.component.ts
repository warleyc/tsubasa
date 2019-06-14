import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMGuerillaQuestTsubasaPointReward,
  MGuerillaQuestTsubasaPointReward
} from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';
import { MGuerillaQuestTsubasaPointRewardService } from './m-guerilla-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-guerilla-quest-tsubasa-point-reward-update',
  templateUrl: './m-guerilla-quest-tsubasa-point-reward-update.component.html'
})
export class MGuerillaQuestTsubasaPointRewardUpdateComponent implements OnInit {
  mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward;
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
    protected mGuerillaQuestTsubasaPointRewardService: MGuerillaQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuerillaQuestTsubasaPointReward }) => {
      this.updateForm(mGuerillaQuestTsubasaPointReward);
      this.mGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointReward;
    });
  }

  updateForm(mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mGuerillaQuestTsubasaPointReward.id,
      stageId: mGuerillaQuestTsubasaPointReward.stageId,
      tsubasaPoint: mGuerillaQuestTsubasaPointReward.tsubasaPoint,
      contentType: mGuerillaQuestTsubasaPointReward.contentType,
      contentId: mGuerillaQuestTsubasaPointReward.contentId,
      contentAmount: mGuerillaQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGuerillaQuestTsubasaPointReward = this.createFromForm();
    if (mGuerillaQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuerillaQuestTsubasaPointRewardService.update(mGuerillaQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mGuerillaQuestTsubasaPointRewardService.create(mGuerillaQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMGuerillaQuestTsubasaPointReward {
    const entity = {
      ...new MGuerillaQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuerillaQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMGuerillaQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
