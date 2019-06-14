import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMInheritActionSkillCost, MInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';
import { MInheritActionSkillCostService } from './m-inherit-action-skill-cost.service';

@Component({
  selector: 'jhi-m-inherit-action-skill-cost-update',
  templateUrl: './m-inherit-action-skill-cost-update.component.html'
})
export class MInheritActionSkillCostUpdateComponent implements OnInit {
  mInheritActionSkillCost: IMInheritActionSkillCost;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    level: [null, [Validators.required]],
    cost: [null, [Validators.required]]
  });

  constructor(
    protected mInheritActionSkillCostService: MInheritActionSkillCostService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mInheritActionSkillCost }) => {
      this.updateForm(mInheritActionSkillCost);
      this.mInheritActionSkillCost = mInheritActionSkillCost;
    });
  }

  updateForm(mInheritActionSkillCost: IMInheritActionSkillCost) {
    this.editForm.patchValue({
      id: mInheritActionSkillCost.id,
      rarity: mInheritActionSkillCost.rarity,
      level: mInheritActionSkillCost.level,
      cost: mInheritActionSkillCost.cost
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mInheritActionSkillCost = this.createFromForm();
    if (mInheritActionSkillCost.id !== undefined) {
      this.subscribeToSaveResponse(this.mInheritActionSkillCostService.update(mInheritActionSkillCost));
    } else {
      this.subscribeToSaveResponse(this.mInheritActionSkillCostService.create(mInheritActionSkillCost));
    }
  }

  private createFromForm(): IMInheritActionSkillCost {
    const entity = {
      ...new MInheritActionSkillCost(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      level: this.editForm.get(['level']).value,
      cost: this.editForm.get(['cost']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMInheritActionSkillCost>>) {
    result.subscribe((res: HttpResponse<IMInheritActionSkillCost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
