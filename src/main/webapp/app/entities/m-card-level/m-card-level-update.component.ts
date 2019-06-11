import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMCardLevel, MCardLevel } from 'app/shared/model/m-card-level.model';
import { MCardLevelService } from './m-card-level.service';

@Component({
  selector: 'jhi-m-card-level-update',
  templateUrl: './m-card-level-update.component.html'
})
export class MCardLevelUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    level: [null, [Validators.required]],
    groupId: [null, [Validators.required]],
    exp: [null, [Validators.required]]
  });

  constructor(protected mCardLevelService: MCardLevelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCardLevel }) => {
      this.updateForm(mCardLevel);
    });
  }

  updateForm(mCardLevel: IMCardLevel) {
    this.editForm.patchValue({
      id: mCardLevel.id,
      rarity: mCardLevel.rarity,
      level: mCardLevel.level,
      groupId: mCardLevel.groupId,
      exp: mCardLevel.exp
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mCardLevel = this.createFromForm();
    if (mCardLevel.id !== undefined) {
      this.subscribeToSaveResponse(this.mCardLevelService.update(mCardLevel));
    } else {
      this.subscribeToSaveResponse(this.mCardLevelService.create(mCardLevel));
    }
  }

  private createFromForm(): IMCardLevel {
    const entity = {
      ...new MCardLevel(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      level: this.editForm.get(['level']).value,
      groupId: this.editForm.get(['groupId']).value,
      exp: this.editForm.get(['exp']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCardLevel>>) {
    result.subscribe((res: HttpResponse<IMCardLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
