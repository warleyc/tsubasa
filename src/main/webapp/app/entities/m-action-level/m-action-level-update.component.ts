import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMActionLevel, MActionLevel } from 'app/shared/model/m-action-level.model';
import { MActionLevelService } from './m-action-level.service';

@Component({
  selector: 'jhi-m-action-level-update',
  templateUrl: './m-action-level-update.component.html'
})
export class MActionLevelUpdateComponent implements OnInit {
  mActionLevel: IMActionLevel;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    level: [null, [Validators.required]],
    exp: [null, [Validators.required]]
  });

  constructor(protected mActionLevelService: MActionLevelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mActionLevel }) => {
      this.updateForm(mActionLevel);
      this.mActionLevel = mActionLevel;
    });
  }

  updateForm(mActionLevel: IMActionLevel) {
    this.editForm.patchValue({
      id: mActionLevel.id,
      rarity: mActionLevel.rarity,
      level: mActionLevel.level,
      exp: mActionLevel.exp
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mActionLevel = this.createFromForm();
    if (mActionLevel.id !== undefined) {
      this.subscribeToSaveResponse(this.mActionLevelService.update(mActionLevel));
    } else {
      this.subscribeToSaveResponse(this.mActionLevelService.create(mActionLevel));
    }
  }

  private createFromForm(): IMActionLevel {
    const entity = {
      ...new MActionLevel(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      level: this.editForm.get(['level']).value,
      exp: this.editForm.get(['exp']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMActionLevel>>) {
    result.subscribe((res: HttpResponse<IMActionLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
