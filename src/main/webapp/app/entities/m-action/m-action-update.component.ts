import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAction, MAction } from 'app/shared/model/m-action.model';
import { MActionService } from './m-action.service';

@Component({
  selector: 'jhi-m-action-update',
  templateUrl: './m-action-update.component.html'
})
export class MActionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    nameRuby: [null, [Validators.required]],
    description: [null, [Validators.required]],
    effectDescription: [null, [Validators.required]],
    initialCommand: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    commandType: [null, [Validators.required]],
    ballConditionGround: [null, [Validators.required]],
    ballConditionLow: [null, [Validators.required]],
    ballConditionHigh: [null, [Validators.required]],
    staminaLvMin: [null, [Validators.required]],
    staminaLvMax: [null, [Validators.required]],
    powerLvMin: [null, [Validators.required]],
    powerLvMax: [null, [Validators.required]],
    blowOffCount: [null, [Validators.required]],
    mShootId: [],
    foulRate: [null, [Validators.required]],
    distanceDecayType: [null, [Validators.required]],
    activateCharacterCount: [null, [Validators.required]],
    actionCutId: [null, [Validators.required]],
    powerupGroup: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mActionService: MActionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAction }) => {
      this.updateForm(mAction);
    });
  }

  updateForm(mAction: IMAction) {
    this.editForm.patchValue({
      id: mAction.id,
      name: mAction.name,
      shortName: mAction.shortName,
      nameRuby: mAction.nameRuby,
      description: mAction.description,
      effectDescription: mAction.effectDescription,
      initialCommand: mAction.initialCommand,
      rarity: mAction.rarity,
      commandType: mAction.commandType,
      ballConditionGround: mAction.ballConditionGround,
      ballConditionLow: mAction.ballConditionLow,
      ballConditionHigh: mAction.ballConditionHigh,
      staminaLvMin: mAction.staminaLvMin,
      staminaLvMax: mAction.staminaLvMax,
      powerLvMin: mAction.powerLvMin,
      powerLvMax: mAction.powerLvMax,
      blowOffCount: mAction.blowOffCount,
      mShootId: mAction.mShootId,
      foulRate: mAction.foulRate,
      distanceDecayType: mAction.distanceDecayType,
      activateCharacterCount: mAction.activateCharacterCount,
      actionCutId: mAction.actionCutId,
      powerupGroup: mAction.powerupGroup
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mAction = this.createFromForm();
    if (mAction.id !== undefined) {
      this.subscribeToSaveResponse(this.mActionService.update(mAction));
    } else {
      this.subscribeToSaveResponse(this.mActionService.create(mAction));
    }
  }

  private createFromForm(): IMAction {
    const entity = {
      ...new MAction(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      nameRuby: this.editForm.get(['nameRuby']).value,
      description: this.editForm.get(['description']).value,
      effectDescription: this.editForm.get(['effectDescription']).value,
      initialCommand: this.editForm.get(['initialCommand']).value,
      rarity: this.editForm.get(['rarity']).value,
      commandType: this.editForm.get(['commandType']).value,
      ballConditionGround: this.editForm.get(['ballConditionGround']).value,
      ballConditionLow: this.editForm.get(['ballConditionLow']).value,
      ballConditionHigh: this.editForm.get(['ballConditionHigh']).value,
      staminaLvMin: this.editForm.get(['staminaLvMin']).value,
      staminaLvMax: this.editForm.get(['staminaLvMax']).value,
      powerLvMin: this.editForm.get(['powerLvMin']).value,
      powerLvMax: this.editForm.get(['powerLvMax']).value,
      blowOffCount: this.editForm.get(['blowOffCount']).value,
      mShootId: this.editForm.get(['mShootId']).value,
      foulRate: this.editForm.get(['foulRate']).value,
      distanceDecayType: this.editForm.get(['distanceDecayType']).value,
      activateCharacterCount: this.editForm.get(['activateCharacterCount']).value,
      actionCutId: this.editForm.get(['actionCutId']).value,
      powerupGroup: this.editForm.get(['powerupGroup']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAction>>) {
    result.subscribe((res: HttpResponse<IMAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
