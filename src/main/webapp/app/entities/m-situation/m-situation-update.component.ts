import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMSituation, MSituation } from 'app/shared/model/m-situation.model';
import { MSituationService } from './m-situation.service';

@Component({
  selector: 'jhi-m-situation-update',
  templateUrl: './m-situation-update.component.html'
})
export class MSituationUpdateComponent implements OnInit {
  mSituation: IMSituation;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    kickoff: [null, [Validators.required]],
    penaltyKick: [null, [Validators.required]],
    matchInterval: [null, [Validators.required]],
    outOfPlay: [null, [Validators.required]],
    foul: [null, [Validators.required]],
    goal: [null, [Validators.required]],
    score: [null, [Validators.required]],
    time: [null, [Validators.required]]
  });

  constructor(protected mSituationService: MSituationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSituation }) => {
      this.updateForm(mSituation);
      this.mSituation = mSituation;
    });
  }

  updateForm(mSituation: IMSituation) {
    this.editForm.patchValue({
      id: mSituation.id,
      kickoff: mSituation.kickoff,
      penaltyKick: mSituation.penaltyKick,
      matchInterval: mSituation.matchInterval,
      outOfPlay: mSituation.outOfPlay,
      foul: mSituation.foul,
      goal: mSituation.goal,
      score: mSituation.score,
      time: mSituation.time
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mSituation = this.createFromForm();
    if (mSituation.id !== undefined) {
      this.subscribeToSaveResponse(this.mSituationService.update(mSituation));
    } else {
      this.subscribeToSaveResponse(this.mSituationService.create(mSituation));
    }
  }

  private createFromForm(): IMSituation {
    const entity = {
      ...new MSituation(),
      id: this.editForm.get(['id']).value,
      kickoff: this.editForm.get(['kickoff']).value,
      penaltyKick: this.editForm.get(['penaltyKick']).value,
      matchInterval: this.editForm.get(['matchInterval']).value,
      outOfPlay: this.editForm.get(['outOfPlay']).value,
      foul: this.editForm.get(['foul']).value,
      goal: this.editForm.get(['goal']).value,
      score: this.editForm.get(['score']).value,
      time: this.editForm.get(['time']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSituation>>) {
    result.subscribe((res: HttpResponse<IMSituation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
