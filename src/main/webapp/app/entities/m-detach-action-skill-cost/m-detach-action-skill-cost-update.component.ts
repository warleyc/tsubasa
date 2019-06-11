import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMDetachActionSkillCost, MDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';
import { MDetachActionSkillCostService } from './m-detach-action-skill-cost.service';

@Component({
  selector: 'jhi-m-detach-action-skill-cost-update',
  templateUrl: './m-detach-action-skill-cost-update.component.html'
})
export class MDetachActionSkillCostUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    cost: [null, [Validators.required]]
  });

  constructor(
    protected mDetachActionSkillCostService: MDetachActionSkillCostService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mDetachActionSkillCost }) => {
      this.updateForm(mDetachActionSkillCost);
    });
  }

  updateForm(mDetachActionSkillCost: IMDetachActionSkillCost) {
    this.editForm.patchValue({
      id: mDetachActionSkillCost.id,
      rarity: mDetachActionSkillCost.rarity,
      cost: mDetachActionSkillCost.cost
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mDetachActionSkillCost = this.createFromForm();
    if (mDetachActionSkillCost.id !== undefined) {
      this.subscribeToSaveResponse(this.mDetachActionSkillCostService.update(mDetachActionSkillCost));
    } else {
      this.subscribeToSaveResponse(this.mDetachActionSkillCostService.create(mDetachActionSkillCost));
    }
  }

  private createFromForm(): IMDetachActionSkillCost {
    const entity = {
      ...new MDetachActionSkillCost(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      cost: this.editForm.get(['cost']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMDetachActionSkillCost>>) {
    result.subscribe((res: HttpResponse<IMDetachActionSkillCost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
