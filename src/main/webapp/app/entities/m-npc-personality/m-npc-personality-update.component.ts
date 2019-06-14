import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMNpcPersonality, MNpcPersonality } from 'app/shared/model/m-npc-personality.model';
import { MNpcPersonalityService } from './m-npc-personality.service';

@Component({
  selector: 'jhi-m-npc-personality-update',
  templateUrl: './m-npc-personality-update.component.html'
})
export class MNpcPersonalityUpdateComponent implements OnInit {
  mNpcPersonality: IMNpcPersonality;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    passingTargetRate: [null, [Validators.required]],
    actionSkillRate: [null, [Validators.required]],
    dribbleMagnification: [null, [Validators.required]],
    passingMagnification: [null, [Validators.required]],
    onetwoMagnification: [null, [Validators.required]],
    shootMagnification: [null, [Validators.required]],
    volleyShootMagnification: [null, [Validators.required]],
    headingShootMagnification: [null, [Validators.required]],
    tackleMagnification: [null, [Validators.required]],
    blockMagnification: [null, [Validators.required]],
    passCutMagnification: [null, [Validators.required]],
    clearMagnification: [null, [Validators.required]],
    competeMagnification: [null, [Validators.required]],
    trapMagnification: [null, [Validators.required]]
  });

  constructor(
    protected mNpcPersonalityService: MNpcPersonalityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mNpcPersonality }) => {
      this.updateForm(mNpcPersonality);
      this.mNpcPersonality = mNpcPersonality;
    });
  }

  updateForm(mNpcPersonality: IMNpcPersonality) {
    this.editForm.patchValue({
      id: mNpcPersonality.id,
      passingTargetRate: mNpcPersonality.passingTargetRate,
      actionSkillRate: mNpcPersonality.actionSkillRate,
      dribbleMagnification: mNpcPersonality.dribbleMagnification,
      passingMagnification: mNpcPersonality.passingMagnification,
      onetwoMagnification: mNpcPersonality.onetwoMagnification,
      shootMagnification: mNpcPersonality.shootMagnification,
      volleyShootMagnification: mNpcPersonality.volleyShootMagnification,
      headingShootMagnification: mNpcPersonality.headingShootMagnification,
      tackleMagnification: mNpcPersonality.tackleMagnification,
      blockMagnification: mNpcPersonality.blockMagnification,
      passCutMagnification: mNpcPersonality.passCutMagnification,
      clearMagnification: mNpcPersonality.clearMagnification,
      competeMagnification: mNpcPersonality.competeMagnification,
      trapMagnification: mNpcPersonality.trapMagnification
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mNpcPersonality = this.createFromForm();
    if (mNpcPersonality.id !== undefined) {
      this.subscribeToSaveResponse(this.mNpcPersonalityService.update(mNpcPersonality));
    } else {
      this.subscribeToSaveResponse(this.mNpcPersonalityService.create(mNpcPersonality));
    }
  }

  private createFromForm(): IMNpcPersonality {
    const entity = {
      ...new MNpcPersonality(),
      id: this.editForm.get(['id']).value,
      passingTargetRate: this.editForm.get(['passingTargetRate']).value,
      actionSkillRate: this.editForm.get(['actionSkillRate']).value,
      dribbleMagnification: this.editForm.get(['dribbleMagnification']).value,
      passingMagnification: this.editForm.get(['passingMagnification']).value,
      onetwoMagnification: this.editForm.get(['onetwoMagnification']).value,
      shootMagnification: this.editForm.get(['shootMagnification']).value,
      volleyShootMagnification: this.editForm.get(['volleyShootMagnification']).value,
      headingShootMagnification: this.editForm.get(['headingShootMagnification']).value,
      tackleMagnification: this.editForm.get(['tackleMagnification']).value,
      blockMagnification: this.editForm.get(['blockMagnification']).value,
      passCutMagnification: this.editForm.get(['passCutMagnification']).value,
      clearMagnification: this.editForm.get(['clearMagnification']).value,
      competeMagnification: this.editForm.get(['competeMagnification']).value,
      trapMagnification: this.editForm.get(['trapMagnification']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMNpcPersonality>>) {
    result.subscribe((res: HttpResponse<IMNpcPersonality>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
