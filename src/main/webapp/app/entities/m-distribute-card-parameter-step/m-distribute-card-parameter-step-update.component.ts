import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMDistributeCardParameterStep, MDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';
import { MDistributeCardParameterStepService } from './m-distribute-card-parameter-step.service';

@Component({
  selector: 'jhi-m-distribute-card-parameter-step-update',
  templateUrl: './m-distribute-card-parameter-step-update.component.html'
})
export class MDistributeCardParameterStepUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isGk: [null, [Validators.required]],
    step: [null, [Validators.required]],
    param: [null, [Validators.required]]
  });

  constructor(
    protected mDistributeCardParameterStepService: MDistributeCardParameterStepService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDistributeCardParameterStep }) => {
      this.updateForm(mDistributeCardParameterStep);
    });
  }

  updateForm(mDistributeCardParameterStep: IMDistributeCardParameterStep) {
    this.editForm.patchValue({
      id: mDistributeCardParameterStep.id,
      isGk: mDistributeCardParameterStep.isGk,
      step: mDistributeCardParameterStep.step,
      param: mDistributeCardParameterStep.param
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mDistributeCardParameterStep = this.createFromForm();
    if (mDistributeCardParameterStep.id !== undefined) {
      this.subscribeToSaveResponse(this.mDistributeCardParameterStepService.update(mDistributeCardParameterStep));
    } else {
      this.subscribeToSaveResponse(this.mDistributeCardParameterStepService.create(mDistributeCardParameterStep));
    }
  }

  private createFromForm(): IMDistributeCardParameterStep {
    const entity = {
      ...new MDistributeCardParameterStep(),
      id: this.editForm.get(['id']).value,
      isGk: this.editForm.get(['isGk']).value,
      step: this.editForm.get(['step']).value,
      param: this.editForm.get(['param']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDistributeCardParameterStep>>) {
    result.subscribe(
      (res: HttpResponse<IMDistributeCardParameterStep>) => this.onSaveSuccess(),
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
