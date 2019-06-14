import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonLoopRewardGroup, MMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';
import { MMarathonLoopRewardGroupService } from './m-marathon-loop-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-loop-reward-group-update',
  templateUrl: './m-marathon-loop-reward-group-update.component.html'
})
export class MMarathonLoopRewardGroupUpdateComponent implements OnInit {
  mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    contentAmount: [null, [Validators.required]],
    weight: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonLoopRewardGroupService: MMarathonLoopRewardGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonLoopRewardGroup }) => {
      this.updateForm(mMarathonLoopRewardGroup);
      this.mMarathonLoopRewardGroup = mMarathonLoopRewardGroup;
    });
  }

  updateForm(mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup) {
    this.editForm.patchValue({
      id: mMarathonLoopRewardGroup.id,
      eventId: mMarathonLoopRewardGroup.eventId,
      contentType: mMarathonLoopRewardGroup.contentType,
      contentId: mMarathonLoopRewardGroup.contentId,
      contentAmount: mMarathonLoopRewardGroup.contentAmount,
      weight: mMarathonLoopRewardGroup.weight
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonLoopRewardGroup = this.createFromForm();
    if (mMarathonLoopRewardGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonLoopRewardGroupService.update(mMarathonLoopRewardGroup));
    } else {
      this.subscribeToSaveResponse(this.mMarathonLoopRewardGroupService.create(mMarathonLoopRewardGroup));
    }
  }

  private createFromForm(): IMMarathonLoopRewardGroup {
    const entity = {
      ...new MMarathonLoopRewardGroup(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      contentAmount: this.editForm.get(['contentAmount']).value,
      weight: this.editForm.get(['weight']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonLoopRewardGroup>>) {
    result.subscribe(
      (res: HttpResponse<IMMarathonLoopRewardGroup>) => this.onSaveSuccess(),
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
