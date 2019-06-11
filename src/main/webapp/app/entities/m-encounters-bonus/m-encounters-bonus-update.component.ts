import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMEncountersBonus, MEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';
import { MEncountersBonusService } from './m-encounters-bonus.service';

@Component({
  selector: 'jhi-m-encounters-bonus-update',
  templateUrl: './m-encounters-bonus-update.component.html'
})
export class MEncountersBonusUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    encountersType: [null, [Validators.required]],
    isSkillSuccess: [null, [Validators.required]],
    threshold: [null, [Validators.required]],
    probabilityBonusValue: [null, [Validators.required]]
  });

  constructor(
    protected mEncountersBonusService: MEncountersBonusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mEncountersBonus }) => {
      this.updateForm(mEncountersBonus);
    });
  }

  updateForm(mEncountersBonus: IMEncountersBonus) {
    this.editForm.patchValue({
      id: mEncountersBonus.id,
      encountersType: mEncountersBonus.encountersType,
      isSkillSuccess: mEncountersBonus.isSkillSuccess,
      threshold: mEncountersBonus.threshold,
      probabilityBonusValue: mEncountersBonus.probabilityBonusValue
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mEncountersBonus = this.createFromForm();
    if (mEncountersBonus.id !== undefined) {
      this.subscribeToSaveResponse(this.mEncountersBonusService.update(mEncountersBonus));
    } else {
      this.subscribeToSaveResponse(this.mEncountersBonusService.create(mEncountersBonus));
    }
  }

  private createFromForm(): IMEncountersBonus {
    const entity = {
      ...new MEncountersBonus(),
      id: this.editForm.get(['id']).value,
      encountersType: this.editForm.get(['encountersType']).value,
      isSkillSuccess: this.editForm.get(['isSkillSuccess']).value,
      threshold: this.editForm.get(['threshold']).value,
      probabilityBonusValue: this.editForm.get(['probabilityBonusValue']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMEncountersBonus>>) {
    result.subscribe((res: HttpResponse<IMEncountersBonus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
