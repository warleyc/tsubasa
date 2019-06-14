import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTriggerEffectBase, MTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';
import { MTriggerEffectBaseService } from './m-trigger-effect-base.service';
import { IMTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';
import { MTriggerEffectRangeService } from 'app/entities/m-trigger-effect-range';

@Component({
  selector: 'jhi-m-trigger-effect-base-update',
  templateUrl: './m-trigger-effect-base-update.component.html'
})
export class MTriggerEffectBaseUpdateComponent implements OnInit {
  mTriggerEffectBase: IMTriggerEffectBase;
  isSaving: boolean;

  mtriggereffectranges: IMTriggerEffectRange[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    effectValue: [null, [Validators.required]],
    triggerProbability: [null, [Validators.required]],
    targetCardParameter: [],
    effectId: [null, [Validators.required]],
    description: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTriggerEffectBaseService: MTriggerEffectBaseService,
    protected mTriggerEffectRangeService: MTriggerEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTriggerEffectBase }) => {
      this.updateForm(mTriggerEffectBase);
      this.mTriggerEffectBase = mTriggerEffectBase;
    });
    this.mTriggerEffectRangeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMTriggerEffectRange[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMTriggerEffectRange[]>) => response.body)
      )
      .subscribe((res: IMTriggerEffectRange[]) => (this.mtriggereffectranges = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTriggerEffectBase: IMTriggerEffectBase) {
    this.editForm.patchValue({
      id: mTriggerEffectBase.id,
      name: mTriggerEffectBase.name,
      rarity: mTriggerEffectBase.rarity,
      effectValue: mTriggerEffectBase.effectValue,
      triggerProbability: mTriggerEffectBase.triggerProbability,
      targetCardParameter: mTriggerEffectBase.targetCardParameter,
      effectId: mTriggerEffectBase.effectId,
      description: mTriggerEffectBase.description,
      idId: mTriggerEffectBase.idId
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
    const mTriggerEffectBase = this.createFromForm();
    if (mTriggerEffectBase.id !== undefined) {
      this.subscribeToSaveResponse(this.mTriggerEffectBaseService.update(mTriggerEffectBase));
    } else {
      this.subscribeToSaveResponse(this.mTriggerEffectBaseService.create(mTriggerEffectBase));
    }
  }

  private createFromForm(): IMTriggerEffectBase {
    const entity = {
      ...new MTriggerEffectBase(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      rarity: this.editForm.get(['rarity']).value,
      effectValue: this.editForm.get(['effectValue']).value,
      triggerProbability: this.editForm.get(['triggerProbability']).value,
      targetCardParameter: this.editForm.get(['targetCardParameter']).value,
      effectId: this.editForm.get(['effectId']).value,
      description: this.editForm.get(['description']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTriggerEffectBase>>) {
    result.subscribe((res: HttpResponse<IMTriggerEffectBase>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMTriggerEffectRangeById(index: number, item: IMTriggerEffectRange) {
    return item.id;
  }
}
