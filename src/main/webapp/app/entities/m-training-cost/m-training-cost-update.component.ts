import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTrainingCost, MTrainingCost } from 'app/shared/model/m-training-cost.model';
import { MTrainingCostService } from './m-training-cost.service';

@Component({
  selector: 'jhi-m-training-cost-update',
  templateUrl: './m-training-cost-update.component.html'
})
export class MTrainingCostUpdateComponent implements OnInit {
  mTrainingCost: IMTrainingCost;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    level: [null, [Validators.required]],
    cost: [null, [Validators.required]]
  });

  constructor(protected mTrainingCostService: MTrainingCostService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTrainingCost }) => {
      this.updateForm(mTrainingCost);
      this.mTrainingCost = mTrainingCost;
    });
  }

  updateForm(mTrainingCost: IMTrainingCost) {
    this.editForm.patchValue({
      id: mTrainingCost.id,
      rarity: mTrainingCost.rarity,
      level: mTrainingCost.level,
      cost: mTrainingCost.cost
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTrainingCost = this.createFromForm();
    if (mTrainingCost.id !== undefined) {
      this.subscribeToSaveResponse(this.mTrainingCostService.update(mTrainingCost));
    } else {
      this.subscribeToSaveResponse(this.mTrainingCostService.create(mTrainingCost));
    }
  }

  private createFromForm(): IMTrainingCost {
    const entity = {
      ...new MTrainingCost(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      level: this.editForm.get(['level']).value,
      cost: this.editForm.get(['cost']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTrainingCost>>) {
    result.subscribe((res: HttpResponse<IMTrainingCost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
