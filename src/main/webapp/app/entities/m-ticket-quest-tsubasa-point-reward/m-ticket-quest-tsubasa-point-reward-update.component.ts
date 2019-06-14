import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import {
  IMTicketQuestTsubasaPointReward,
  MTicketQuestTsubasaPointReward
} from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';
import { MTicketQuestTsubasaPointRewardService } from './m-ticket-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-ticket-quest-tsubasa-point-reward-update',
  templateUrl: './m-ticket-quest-tsubasa-point-reward-update.component.html'
})
export class MTicketQuestTsubasaPointRewardUpdateComponent implements OnInit {
  mTicketQuestTsubasaPointReward: IMTicketQuestTsubasaPointReward;
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
    protected mTicketQuestTsubasaPointRewardService: MTicketQuestTsubasaPointRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTicketQuestTsubasaPointReward }) => {
      this.updateForm(mTicketQuestTsubasaPointReward);
      this.mTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointReward;
    });
  }

  updateForm(mTicketQuestTsubasaPointReward: IMTicketQuestTsubasaPointReward) {
    this.editForm.patchValue({
      id: mTicketQuestTsubasaPointReward.id,
      stageId: mTicketQuestTsubasaPointReward.stageId,
      tsubasaPoint: mTicketQuestTsubasaPointReward.tsubasaPoint,
      contentType: mTicketQuestTsubasaPointReward.contentType,
      contentId: mTicketQuestTsubasaPointReward.contentId,
      contentAmount: mTicketQuestTsubasaPointReward.contentAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTicketQuestTsubasaPointReward = this.createFromForm();
    if (mTicketQuestTsubasaPointReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mTicketQuestTsubasaPointRewardService.update(mTicketQuestTsubasaPointReward));
    } else {
      this.subscribeToSaveResponse(this.mTicketQuestTsubasaPointRewardService.create(mTicketQuestTsubasaPointReward));
    }
  }

  private createFromForm(): IMTicketQuestTsubasaPointReward {
    const entity = {
      ...new MTicketQuestTsubasaPointReward(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      tsubasaPoint: this.editForm.get(['tsubasaPoint']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTicketQuestTsubasaPointReward>>) {
    result.subscribe(
      (res: HttpResponse<IMTicketQuestTsubasaPointReward>) => this.onSaveSuccess(),
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
