import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTeamEffectBase, MTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';
import { MTeamEffectBaseService } from './m-team-effect-base.service';
import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from 'app/entities/m-passive-effect-range';

@Component({
  selector: 'jhi-m-team-effect-base-update',
  templateUrl: './m-team-effect-base-update.component.html'
})
export class MTeamEffectBaseUpdateComponent implements OnInit {
  mTeamEffectBase: IMTeamEffectBase;
  isSaving: boolean;

  mpassiveeffectranges: IMPassiveEffectRange[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    effectValueMin: [null, [Validators.required]],
    effectValueMax: [null, [Validators.required]],
    triggerProbabilityMin: [null, [Validators.required]],
    triggerProbabilityMax: [null, [Validators.required]],
    effectId: [null, [Validators.required]],
    effectValueMin2: [null, [Validators.required]],
    effectValueMax2: [null, [Validators.required]],
    triggerProbabilityMin2: [null, [Validators.required]],
    triggerProbabilityMax2: [null, [Validators.required]],
    effectId2: [],
    description: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTeamEffectBaseService: MTeamEffectBaseService,
    protected mPassiveEffectRangeService: MPassiveEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTeamEffectBase }) => {
      this.updateForm(mTeamEffectBase);
      this.mTeamEffectBase = mTeamEffectBase;
    });
    this.mPassiveEffectRangeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPassiveEffectRange[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPassiveEffectRange[]>) => response.body)
      )
      .subscribe((res: IMPassiveEffectRange[]) => (this.mpassiveeffectranges = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTeamEffectBase: IMTeamEffectBase) {
    this.editForm.patchValue({
      id: mTeamEffectBase.id,
      name: mTeamEffectBase.name,
      rarity: mTeamEffectBase.rarity,
      effectValueMin: mTeamEffectBase.effectValueMin,
      effectValueMax: mTeamEffectBase.effectValueMax,
      triggerProbabilityMin: mTeamEffectBase.triggerProbabilityMin,
      triggerProbabilityMax: mTeamEffectBase.triggerProbabilityMax,
      effectId: mTeamEffectBase.effectId,
      effectValueMin2: mTeamEffectBase.effectValueMin2,
      effectValueMax2: mTeamEffectBase.effectValueMax2,
      triggerProbabilityMin2: mTeamEffectBase.triggerProbabilityMin2,
      triggerProbabilityMax2: mTeamEffectBase.triggerProbabilityMax2,
      effectId2: mTeamEffectBase.effectId2,
      description: mTeamEffectBase.description,
      idId: mTeamEffectBase.idId
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
    const mTeamEffectBase = this.createFromForm();
    if (mTeamEffectBase.id !== undefined) {
      this.subscribeToSaveResponse(this.mTeamEffectBaseService.update(mTeamEffectBase));
    } else {
      this.subscribeToSaveResponse(this.mTeamEffectBaseService.create(mTeamEffectBase));
    }
  }

  private createFromForm(): IMTeamEffectBase {
    const entity = {
      ...new MTeamEffectBase(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      rarity: this.editForm.get(['rarity']).value,
      effectValueMin: this.editForm.get(['effectValueMin']).value,
      effectValueMax: this.editForm.get(['effectValueMax']).value,
      triggerProbabilityMin: this.editForm.get(['triggerProbabilityMin']).value,
      triggerProbabilityMax: this.editForm.get(['triggerProbabilityMax']).value,
      effectId: this.editForm.get(['effectId']).value,
      effectValueMin2: this.editForm.get(['effectValueMin2']).value,
      effectValueMax2: this.editForm.get(['effectValueMax2']).value,
      triggerProbabilityMin2: this.editForm.get(['triggerProbabilityMin2']).value,
      triggerProbabilityMax2: this.editForm.get(['triggerProbabilityMax2']).value,
      effectId2: this.editForm.get(['effectId2']).value,
      description: this.editForm.get(['description']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTeamEffectBase>>) {
    result.subscribe((res: HttpResponse<IMTeamEffectBase>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
