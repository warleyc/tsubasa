import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonLoopReward, MMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';
import { MMarathonLoopRewardService } from './m-marathon-loop-reward.service';

@Component({
  selector: 'jhi-m-marathon-loop-reward-update',
  templateUrl: './m-marathon-loop-reward-update.component.html'
})
export class MMarathonLoopRewardUpdateComponent implements OnInit {
  mMarathonLoopReward: IMMarathonLoopReward;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    loopPoint: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonLoopRewardService: MMarathonLoopRewardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonLoopReward }) => {
      this.updateForm(mMarathonLoopReward);
      this.mMarathonLoopReward = mMarathonLoopReward;
    });
  }

  updateForm(mMarathonLoopReward: IMMarathonLoopReward) {
    this.editForm.patchValue({
      id: mMarathonLoopReward.id,
      eventId: mMarathonLoopReward.eventId,
      loopPoint: mMarathonLoopReward.loopPoint
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonLoopReward = this.createFromForm();
    if (mMarathonLoopReward.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonLoopRewardService.update(mMarathonLoopReward));
    } else {
      this.subscribeToSaveResponse(this.mMarathonLoopRewardService.create(mMarathonLoopReward));
    }
  }

  private createFromForm(): IMMarathonLoopReward {
    const entity = {
      ...new MMarathonLoopReward(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      loopPoint: this.editForm.get(['loopPoint']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonLoopReward>>) {
    result.subscribe((res: HttpResponse<IMMarathonLoopReward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
