import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMMatchOption, MMatchOption } from 'app/shared/model/m-match-option.model';
import { MMatchOptionService } from './m-match-option.service';
import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from 'app/entities/m-passive-effect-range';

@Component({
  selector: 'jhi-m-match-option-update',
  templateUrl: './m-match-option-update.component.html'
})
export class MMatchOptionUpdateComponent implements OnInit {
  mMatchOption: IMMatchOption;
  isSaving: boolean;

  mpassiveeffectranges: IMPassiveEffectRange[];

  editForm = this.fb.group({
    id: [],
    passiveEffectId: [],
    passiveEffectValue: [null, [Validators.required]],
    activateFullPowerType: [null, [Validators.required]],
    attributeMagnification: [null, [Validators.required]],
    useStaminaMagnification: [null, [Validators.required]],
    isIgnoreTeamSkill: [null, [Validators.required]],
    staminaInfinityType: [null, [Validators.required]],
    mpassiveeffectrangeId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mMatchOptionService: MMatchOptionService,
    protected mPassiveEffectRangeService: MPassiveEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMatchOption }) => {
      this.updateForm(mMatchOption);
      this.mMatchOption = mMatchOption;
    });
    this.mPassiveEffectRangeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPassiveEffectRange[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPassiveEffectRange[]>) => response.body)
      )
      .subscribe((res: IMPassiveEffectRange[]) => (this.mpassiveeffectranges = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mMatchOption: IMMatchOption) {
    this.editForm.patchValue({
      id: mMatchOption.id,
      passiveEffectId: mMatchOption.passiveEffectId,
      passiveEffectValue: mMatchOption.passiveEffectValue,
      activateFullPowerType: mMatchOption.activateFullPowerType,
      attributeMagnification: mMatchOption.attributeMagnification,
      useStaminaMagnification: mMatchOption.useStaminaMagnification,
      isIgnoreTeamSkill: mMatchOption.isIgnoreTeamSkill,
      staminaInfinityType: mMatchOption.staminaInfinityType,
      mpassiveeffectrangeId: mMatchOption.mpassiveeffectrangeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mMatchOption = this.createFromForm();
    if (mMatchOption.id !== undefined) {
      this.subscribeToSaveResponse(this.mMatchOptionService.update(mMatchOption));
    } else {
      this.subscribeToSaveResponse(this.mMatchOptionService.create(mMatchOption));
    }
  }

  private createFromForm(): IMMatchOption {
    const entity = {
      ...new MMatchOption(),
      id: this.editForm.get(['id']).value,
      passiveEffectId: this.editForm.get(['passiveEffectId']).value,
      passiveEffectValue: this.editForm.get(['passiveEffectValue']).value,
      activateFullPowerType: this.editForm.get(['activateFullPowerType']).value,
      attributeMagnification: this.editForm.get(['attributeMagnification']).value,
      useStaminaMagnification: this.editForm.get(['useStaminaMagnification']).value,
      isIgnoreTeamSkill: this.editForm.get(['isIgnoreTeamSkill']).value,
      staminaInfinityType: this.editForm.get(['staminaInfinityType']).value,
      mpassiveeffectrangeId: this.editForm.get(['mpassiveeffectrangeId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMatchOption>>) {
    result.subscribe((res: HttpResponse<IMMatchOption>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMPassiveEffectRangeById(index: number, item: IMPassiveEffectRange) {
    return item.id;
  }
}
