import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTriggerEffectRange, MTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';
import { MTriggerEffectRangeService } from './m-trigger-effect-range.service';

@Component({
  selector: 'jhi-m-trigger-effect-range-update',
  templateUrl: './m-trigger-effect-range-update.component.html'
})
export class MTriggerEffectRangeUpdateComponent implements OnInit {
  mTriggerEffectRange: IMTriggerEffectRange;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    effectType: [null, [Validators.required]],
    targetPositionGk: [null, [Validators.required]],
    targetPositionFw: [null, [Validators.required]],
    targetPositionOmf: [null, [Validators.required]],
    targetPositionDmf: [null, [Validators.required]],
    targetPositionDf: [null, [Validators.required]],
    targetAttribute: [],
    targetNationalityGroupId: [],
    targetTeamGroupId: [],
    targetCharacterGroupId: [],
    targetFormationGroupId: [],
    targetActionCommand: []
  });

  constructor(
    protected mTriggerEffectRangeService: MTriggerEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTriggerEffectRange }) => {
      this.updateForm(mTriggerEffectRange);
      this.mTriggerEffectRange = mTriggerEffectRange;
    });
  }

  updateForm(mTriggerEffectRange: IMTriggerEffectRange) {
    this.editForm.patchValue({
      id: mTriggerEffectRange.id,
      effectType: mTriggerEffectRange.effectType,
      targetPositionGk: mTriggerEffectRange.targetPositionGk,
      targetPositionFw: mTriggerEffectRange.targetPositionFw,
      targetPositionOmf: mTriggerEffectRange.targetPositionOmf,
      targetPositionDmf: mTriggerEffectRange.targetPositionDmf,
      targetPositionDf: mTriggerEffectRange.targetPositionDf,
      targetAttribute: mTriggerEffectRange.targetAttribute,
      targetNationalityGroupId: mTriggerEffectRange.targetNationalityGroupId,
      targetTeamGroupId: mTriggerEffectRange.targetTeamGroupId,
      targetCharacterGroupId: mTriggerEffectRange.targetCharacterGroupId,
      targetFormationGroupId: mTriggerEffectRange.targetFormationGroupId,
      targetActionCommand: mTriggerEffectRange.targetActionCommand
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTriggerEffectRange = this.createFromForm();
    if (mTriggerEffectRange.id !== undefined) {
      this.subscribeToSaveResponse(this.mTriggerEffectRangeService.update(mTriggerEffectRange));
    } else {
      this.subscribeToSaveResponse(this.mTriggerEffectRangeService.create(mTriggerEffectRange));
    }
  }

  private createFromForm(): IMTriggerEffectRange {
    const entity = {
      ...new MTriggerEffectRange(),
      id: this.editForm.get(['id']).value,
      effectType: this.editForm.get(['effectType']).value,
      targetPositionGk: this.editForm.get(['targetPositionGk']).value,
      targetPositionFw: this.editForm.get(['targetPositionFw']).value,
      targetPositionOmf: this.editForm.get(['targetPositionOmf']).value,
      targetPositionDmf: this.editForm.get(['targetPositionDmf']).value,
      targetPositionDf: this.editForm.get(['targetPositionDf']).value,
      targetAttribute: this.editForm.get(['targetAttribute']).value,
      targetNationalityGroupId: this.editForm.get(['targetNationalityGroupId']).value,
      targetTeamGroupId: this.editForm.get(['targetTeamGroupId']).value,
      targetCharacterGroupId: this.editForm.get(['targetCharacterGroupId']).value,
      targetFormationGroupId: this.editForm.get(['targetFormationGroupId']).value,
      targetActionCommand: this.editForm.get(['targetActionCommand']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTriggerEffectRange>>) {
    result.subscribe((res: HttpResponse<IMTriggerEffectRange>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
