import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMAdventQuestTsubasaPointReward,
  MAdventQuestTsubasaPointReward
} from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';
import { MAdventQuestTsubasaPointRewardService } from './m-advent-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-advent-quest-tsubasa-point-reward-update',
  templateUrl: './m-advent-quest-tsubasa-point-reward-update.component.html'
})
export class MAdventQuestTsubasaPointRewardUpdateComponent implements OnInit {
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
    protected mAdventQuestTsubasaPointRewardService: MAdventQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAdventQuestTsubasaPointReward }) => {
      this.updateForm(mAdventQuestTsubasaPointReward);
    });
  }

  updateForm(mAdventQuestTsubasaPointReward: IMAdventQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mAdventQuestTsubasaPointReward.id,
      stageId: mAdventQuestTsubasaPointReward.stageId,
      tsubasaPoint: mAdventQuestTsubasaPointReward.tsubasaPoint,
      contentType: mAdventQuestTsubasaPointReward.contentType,
      contentId: mAdventQuestTsubasaPointReward.contentId,
      contentAmount: mAdventQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mAdventQuestTsubasaPointReward = this.createFromForm();
    if (mAdventQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mAdventQuestTsubasaPointRewardService.update(mAdventQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mAdventQuestTsubasaPointRewardService.create(mAdventQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMAdventQuestTsubasaPointReward {
    const entity = {
      ...new MAdventQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAdventQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMAdventQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
