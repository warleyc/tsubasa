import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMGoalJudgement, MGoalJudgement } from 'app/shared/model/m-goal-judgement.model';
import { MGoalJudgementService } from './m-goal-judgement.service';

@Component({
  selector: 'jhi-m-goal-judgement-update',
  templateUrl: './m-goal-judgement-update.component.html'
})
export class MGoalJudgementUpdateComponent implements OnInit {
  mGoalJudgement: IMGoalJudgement;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    judgementId: [null, [Validators.required]],
    encountersType: [null, [Validators.required]],
    successRate: [null, [Validators.required]],
    goalPostRate: [null, [Validators.required]],
    ballPushRate: [null, [Validators.required]],
    clearRate: [null, [Validators.required]]
  });

  constructor(protected mGoalJudgementService: MGoalJudgementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGoalJudgement }) => {
      this.updateForm(mGoalJudgement);
      this.mGoalJudgement = mGoalJudgement;
    });
  }

  updateForm(mGoalJudgement: IMGoalJudgement) {
    this.editForm.patchValue({
      id: mGoalJudgement.id,
      judgementId: mGoalJudgement.judgementId,
      encountersType: mGoalJudgement.encountersType,
      successRate: mGoalJudgement.successRate,
      goalPostRate: mGoalJudgement.goalPostRate,
      ballPushRate: mGoalJudgement.ballPushRate,
      clearRate: mGoalJudgement.clearRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mGoalJudgement = this.createFromForm();
    if (mGoalJudgement.id !== undefined) {
      this.subscribeToSaveResponse(this.mGoalJudgementService.update(mGoalJudgement));
    } else {
      this.subscribeToSaveResponse(this.mGoalJudgementService.create(mGoalJudgement));
    }
  }

  private createFromForm(): IMGoalJudgement {
    const entity = {
      ...new MGoalJudgement(),
      id: this.editForm.get(['id']).value,
      judgementId: this.editForm.get(['judgementId']).value,
      encountersType: this.editForm.get(['encountersType']).value,
      successRate: this.editForm.get(['successRate']).value,
      goalPostRate: this.editForm.get(['goalPostRate']).value,
      ballPushRate: this.editForm.get(['ballPushRate']).value,
      clearRate: this.editForm.get(['clearRate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGoalJudgement>>) {
    result.subscribe((res: HttpResponse<IMGoalJudgement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
