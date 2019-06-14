import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMTeamEffectLevel, MTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';
import { MTeamEffectLevelService } from './m-team-effect-level.service';

@Component({
  selector: 'jhi-m-team-effect-level-update',
  templateUrl: './m-team-effect-level-update.component.html'
})
export class MTeamEffectLevelUpdateComponent implements OnInit {
  mTeamEffectLevel: IMTeamEffectLevel;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rarity: [null, [Validators.required]],
    level: [null, [Validators.required]],
    exp: [null, [Validators.required]]
  });

  constructor(
    protected mTeamEffectLevelService: MTeamEffectLevelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTeamEffectLevel }) => {
      this.updateForm(mTeamEffectLevel);
      this.mTeamEffectLevel = mTeamEffectLevel;
    });
  }

  updateForm(mTeamEffectLevel: IMTeamEffectLevel) {
    this.editForm.patchValue({
      id: mTeamEffectLevel.id,
      rarity: mTeamEffectLevel.rarity,
      level: mTeamEffectLevel.level,
      exp: mTeamEffectLevel.exp
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mTeamEffectLevel = this.createFromForm();
    if (mTeamEffectLevel.id !== undefined) {
      this.subscribeToSaveResponse(this.mTeamEffectLevelService.update(mTeamEffectLevel));
    } else {
      this.subscribeToSaveResponse(this.mTeamEffectLevelService.create(mTeamEffectLevel));
    }
  }

  private createFromForm(): IMTeamEffectLevel {
    const entity = {
      ...new MTeamEffectLevel(),
      id: this.editForm.get(['id']).value,
      rarity: this.editForm.get(['rarity']).value,
      level: this.editForm.get(['level']).value,
      exp: this.editForm.get(['exp']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTeamEffectLevel>>) {
    result.subscribe((res: HttpResponse<IMTeamEffectLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
