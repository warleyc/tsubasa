import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMDistributeCardParameter, MDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';
import { MDistributeCardParameterService } from './m-distribute-card-parameter.service';

@Component({
  selector: 'jhi-m-distribute-card-parameter-update',
  templateUrl: './m-distribute-card-parameter-update.component.html'
})
export class MDistributeCardParameterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    isGk: [null, [Validators.required]],
    maxStepCount: [null, [Validators.required]],
    maxTotalStepCount: [null, [Validators.required]],
    distributePointByStep: [null, [Validators.required]]
  });

  constructor(
    protected mDistributeCardParameterService: MDistributeCardParameterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDistributeCardParameter }) => {
      this.updateForm(mDistributeCardParameter);
    });
  }

  updateForm(mDistributeCardParameter: IMDistributeCardParameter) {
    this.editForm.patchValue({
      id: mDistributeCardParameter.id,
      rarity: mDistributeCardParameter.rarity,
      isGk: mDistributeCardParameter.isGk,
      maxStepCount: mDistributeCardParameter.maxStepCount,
      maxTotalStepCount: mDistributeCardParameter.maxTotalStepCount,
      distributePointByStep: mDistributeCardParameter.distributePointByStep
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mDistributeCardParameter = this.createFromForm();
    if (mDistributeCardParameter.id !== undefined) {
      this.subscribeToSaveResponse(this.mDistributeCardParameterService.update(mDistributeCardParameter));
    } else {
      this.subscribeToSaveResponse(this.mDistributeCardParameterService.create(mDistributeCardParameter));
    }
  }

  private createFromForm(): IMDistributeCardParameter {
    const entity = {
      ...new MDistributeCardParameter(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      isGk: this.editForm.get(['isGk']).value,
      maxStepCount: this.editForm.get(['maxStepCount']).value,
      maxTotalStepCount: this.editForm.get(['maxTotalStepCount']).value,
      distributePointByStep: this.editForm.get(['distributePointByStep']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDistributeCardParameter>>) {
    result.subscribe(
      (res: HttpResponse<IMDistributeCardParameter>) => this.onSaveSuccess(),
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
