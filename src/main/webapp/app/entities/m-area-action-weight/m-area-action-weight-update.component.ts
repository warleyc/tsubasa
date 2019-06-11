import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMAreaActionWeight, MAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';
import { MAreaActionWeightService } from './m-area-action-weight.service';

@Component({
  selector: 'jhi-m-area-action-weight-update',
  templateUrl: './m-area-action-weight-update.component.html'
})
export class MAreaActionWeightUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    areaType: [null, [Validators.required]],
    dribbleRate: [null, [Validators.required]],
    passingRate: [null, [Validators.required]],
    onetwoRate: [null, [Validators.required]],
    shootRate: [null, [Validators.required]],
    volleyShootRate: [null, [Validators.required]],
    headingShootRate: [null, [Validators.required]],
    tackleRate: [null, [Validators.required]],
    blockRate: [null, [Validators.required]],
    passCutRate: [null, [Validators.required]],
    clearRate: [null, [Validators.required]],
    competeRate: [null, [Validators.required]],
    trapRate: [null, [Validators.required]]
  });

  constructor(
    protected mAreaActionWeightService: MAreaActionWeightService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAreaActionWeight }) => {
      this.updateForm(mAreaActionWeight);
    });
  }

  updateForm(mAreaActionWeight: IMAreaActionWeight) {
    this.editForm.patchValue({
      id: mAreaActionWeight.id,
      areaType: mAreaActionWeight.areaType,
      dribbleRate: mAreaActionWeight.dribbleRate,
      passingRate: mAreaActionWeight.passingRate,
      onetwoRate: mAreaActionWeight.onetwoRate,
      shootRate: mAreaActionWeight.shootRate,
      volleyShootRate: mAreaActionWeight.volleyShootRate,
      headingShootRate: mAreaActionWeight.headingShootRate,
      tackleRate: mAreaActionWeight.tackleRate,
      blockRate: mAreaActionWeight.blockRate,
      passCutRate: mAreaActionWeight.passCutRate,
      clearRate: mAreaActionWeight.clearRate,
      competeRate: mAreaActionWeight.competeRate,
      trapRate: mAreaActionWeight.trapRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mAreaActionWeight = this.createFromForm();
    if (mAreaActionWeight.id !== undefined) {
      this.subscribeToSaveResponse(this.mAreaActionWeightService.update(mAreaActionWeight));
    } else {
      this.subscribeToSaveResponse(this.mAreaActionWeightService.create(mAreaActionWeight));
    }
  }

  private createFromForm(): IMAreaActionWeight {
    const entity = {
      ...new MAreaActionWeight(),
      id: this.editForm.get(['id']).value,
      areaType: this.editForm.get(['areaType']).value,
      dribbleRate: this.editForm.get(['dribbleRate']).value,
      passingRate: this.editForm.get(['passingRate']).value,
      onetwoRate: this.editForm.get(['onetwoRate']).value,
      shootRate: this.editForm.get(['shootRate']).value,
      volleyShootRate: this.editForm.get(['volleyShootRate']).value,
      headingShootRate: this.editForm.get(['headingShootRate']).value,
      tackleRate: this.editForm.get(['tackleRate']).value,
      blockRate: this.editForm.get(['blockRate']).value,
      passCutRate: this.editForm.get(['passCutRate']).value,
      clearRate: this.editForm.get(['clearRate']).value,
      competeRate: this.editForm.get(['competeRate']).value,
      trapRate: this.editForm.get(['trapRate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAreaActionWeight>>) {
    result.subscribe((res: HttpResponse<IMAreaActionWeight>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
