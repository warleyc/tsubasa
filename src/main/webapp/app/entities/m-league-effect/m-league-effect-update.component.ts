import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMLeagueEffect, MLeagueEffect } from 'app/shared/model/m-league-effect.model';
import { MLeagueEffectService } from './m-league-effect.service';

@Component({
  selector: 'jhi-m-league-effect-update',
  templateUrl: './m-league-effect-update.component.html'
})
export class MLeagueEffectUpdateComponent implements OnInit {
  mLeagueEffect: IMLeagueEffect;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    effectType: [null, [Validators.required]],
    leagueHierarchy: [null, [Validators.required]],
    rate: [null, [Validators.required]],
    price: [null, [Validators.required]]
  });

  constructor(protected mLeagueEffectService: MLeagueEffectService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLeagueEffect }) => {
      this.updateForm(mLeagueEffect);
      this.mLeagueEffect = mLeagueEffect;
    });
  }

  updateForm(mLeagueEffect: IMLeagueEffect) {
    this.editForm.patchValue({
      id: mLeagueEffect.id,
      effectType: mLeagueEffect.effectType,
      leagueHierarchy: mLeagueEffect.leagueHierarchy,
      rate: mLeagueEffect.rate,
      price: mLeagueEffect.price
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mLeagueEffect = this.createFromForm();
    if (mLeagueEffect.id !== undefined) {
      this.subscribeToSaveResponse(this.mLeagueEffectService.update(mLeagueEffect));
    } else {
      this.subscribeToSaveResponse(this.mLeagueEffectService.create(mLeagueEffect));
    }
  }

  private createFromForm(): IMLeagueEffect {
    const entity = {
      ...new MLeagueEffect(),
      id: this.editForm.get(['id']).value,
      effectType: this.editForm.get(['effectType']).value,
      leagueHierarchy: this.editForm.get(['leagueHierarchy']).value,
      rate: this.editForm.get(['rate']).value,
      price: this.editForm.get(['price']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLeagueEffect>>) {
    result.subscribe((res: HttpResponse<IMLeagueEffect>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
