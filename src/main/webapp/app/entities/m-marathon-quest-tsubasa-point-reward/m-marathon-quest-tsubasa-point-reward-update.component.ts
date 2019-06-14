import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMMarathonQuestTsubasaPointReward,
  MMarathonQuestTsubasaPointReward
} from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';
import { MMarathonQuestTsubasaPointRewardService } from './m-marathon-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-marathon-quest-tsubasa-point-reward-update',
  templateUrl: './m-marathon-quest-tsubasa-point-reward-update.component.html'
})
export class MMarathonQuestTsubasaPointRewardUpdateComponent implements OnInit {
  mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward;
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
    protected mMarathonQuestTsubasaPointRewardService: MMarathonQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonQuestTsubasaPointReward }) => {
      this.updateForm(mMarathonQuestTsubasaPointReward);
      this.mMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointReward;
    });
  }

  updateForm(mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mMarathonQuestTsubasaPointReward.id,
      stageId: mMarathonQuestTsubasaPointReward.stageId,
      tsubasaPoint: mMarathonQuestTsubasaPointReward.tsubasaPoint,
      contentType: mMarathonQuestTsubasaPointReward.contentType,
      contentId: mMarathonQuestTsubasaPointReward.contentId,
      contentAmount: mMarathonQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonQuestTsubasaPointReward = this.createFromForm();
    if (mMarathonQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonQuestTsubasaPointRewardService.update(mMarathonQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mMarathonQuestTsubasaPointRewardService.create(mMarathonQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMMarathonQuestTsubasaPointReward {
    const entity = {
      ...new MMarathonQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
