import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMMarathonBoostSchedule, MMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';
import { MMarathonBoostScheduleService } from './m-marathon-boost-schedule.service';

@Component({
  selector: 'jhi-m-marathon-boost-schedule-update',
  templateUrl: './m-marathon-boost-schedule-update.component.html'
})
export class MMarathonBoostScheduleUpdateComponent implements OnInit {
  mMarathonBoostSchedule: IMMarathonBoostSchedule;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    eventId: [null, [Validators.required]],
    boostRatio: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    endAt: [null, [Validators.required]]
  });

  constructor(
    protected mMarathonBoostScheduleService: MMarathonBoostScheduleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonBoostSchedule }) => {
      this.updateForm(mMarathonBoostSchedule);
      this.mMarathonBoostSchedule = mMarathonBoostSchedule;
    });
  }

  updateForm(mMarathonBoostSchedule: IMMarathonBoostSchedule) {
    this.editForm.patchValue({
      id: mMarathonBoostSchedule.id,
      eventId: mMarathonBoostSchedule.eventId,
      boostRatio: mMarathonBoostSchedule.boostRatio,
      startAt: mMarathonBoostSchedule.startAt,
      endAt: mMarathonBoostSchedule.endAt
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMarathonBoostSchedule = this.createFromForm();
    if (mMarathonBoostSchedule.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonBoostScheduleService.update(mMarathonBoostSchedule));
    } else {
      this.subscribeToSaveResponse(this.mMarathonBoostScheduleService.create(mMarathonBoostSchedule));
    }
  }

  private createFromForm(): IMMarathonBoostSchedule {
    const entity = {
      ...new MMarathonBoostSchedule(),
      id: this.editForm.get(['id']).value,
      eventId: this.editForm.get(['eventId']).value,
      boostRatio: this.editForm.get(['boostRatio']).value,
      startAt: this.editForm.get(['startAt']).value,
      endAt: this.editForm.get(['endAt']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonBoostSchedule>>) {
    result.subscribe((res: HttpResponse<IMMarathonBoostSchedule>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
