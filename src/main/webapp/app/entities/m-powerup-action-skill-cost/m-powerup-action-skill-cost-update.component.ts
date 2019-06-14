import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMPowerupActionSkillCost, MPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';
import { MPowerupActionSkillCostService } from './m-powerup-action-skill-cost.service';

@Component({
  selector: 'jhi-m-powerup-action-skill-cost-update',
  templateUrl: './m-powerup-action-skill-cost-update.component.html'
})
export class MPowerupActionSkillCostUpdateComponent implements OnInit {
  mPowerupActionSkillCost: IMPowerupActionSkillCost;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    actionRarity: [null, [Validators.required]],
    actionLevel: [null, [Validators.required]],
    cost: [null, [Validators.required]]
  });

  constructor(
    protected mPowerupActionSkillCostService: MPowerupActionSkillCostService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPowerupActionSkillCost }) => {
      this.updateForm(mPowerupActionSkillCost);
      this.mPowerupActionSkillCost = mPowerupActionSkillCost;
    });
  }

  updateForm(mPowerupActionSkillCost: IMPowerupActionSkillCost) {
    this.editForm.patchValue({
      id: mPowerupActionSkillCost.id,
      actionRarity: mPowerupActionSkillCost.actionRarity,
      actionLevel: mPowerupActionSkillCost.actionLevel,
      cost: mPowerupActionSkillCost.cost
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mPowerupActionSkillCost = this.createFromForm();
    if (mPowerupActionSkillCost.id !== undefined) {
      this.subscribeToSaveResponse(this.mPowerupActionSkillCostService.update(mPowerupActionSkillCost));
    } else {
      this.subscribeToSaveResponse(this.mPowerupActionSkillCostService.create(mPowerupActionSkillCost));
    }
  }

  private createFromForm(): IMPowerupActionSkillCost {
    const entity = {
      ...new MPowerupActionSkillCost(),
      id: this.editForm.get(['id']).value,
      actionRarity: this.editForm.get(['actionRarity']).value,
      actionLevel: this.editForm.get(['actionLevel']).value,
      cost: this.editForm.get(['cost']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPowerupActionSkillCost>>) {
    result.subscribe((res: HttpResponse<IMPowerupActionSkillCost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
