import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMFormation, MFormation } from 'app/shared/model/m-formation.model';
import { MFormationService } from './m-formation.service';
import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from 'app/entities/m-passive-effect-range';

@Component({
  selector: 'jhi-m-formation-update',
  templateUrl: './m-formation-update.component.html'
})
export class MFormationUpdateComponent implements OnInit {
  mFormation: IMFormation;
  isSaving: boolean;

  mpassiveeffectranges: IMPassiveEffectRange[];

  editForm = this.fb.group({
    id: [],
    effectValue: [null, [Validators.required]],
    effectProbability: [null, [Validators.required]],
    formationArrangementFw: [null, [Validators.required]],
    formationArrangementOmf: [null, [Validators.required]],
    formationArrangementDmf: [null, [Validators.required]],
    formationArrangementDf: [null, [Validators.required]],
    effectId: [],
    description: [null, [Validators.required]],
    shortDescription: [null, [Validators.required]],
    name: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    startingUniformNos: [null, [Validators.required]],
    subUniformNos: [null, [Validators.required]],
    exType: [],
    matchFormationId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mFormationService: MFormationService,
    protected mPassiveEffectRangeService: MPassiveEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mFormation }) => {
      this.updateForm(mFormation);
      this.mFormation = mFormation;
    });
    this.mPassiveEffectRangeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMPassiveEffectRange[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMPassiveEffectRange[]>) => response.body)
      )
      .subscribe((res: IMPassiveEffectRange[]) => (this.mpassiveeffectranges = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mFormation: IMFormation) {
    this.editForm.patchValue({
      id: mFormation.id,
      effectValue: mFormation.effectValue,
      effectProbability: mFormation.effectProbability,
      formationArrangementFw: mFormation.formationArrangementFw,
      formationArrangementOmf: mFormation.formationArrangementOmf,
      formationArrangementDmf: mFormation.formationArrangementDmf,
      formationArrangementDf: mFormation.formationArrangementDf,
      effectId: mFormation.effectId,
      description: mFormation.description,
      shortDescription: mFormation.shortDescription,
      name: mFormation.name,
      thumbnailAssetName: mFormation.thumbnailAssetName,
      startingUniformNos: mFormation.startingUniformNos,
      subUniformNos: mFormation.subUniformNos,
      exType: mFormation.exType,
      matchFormationId: mFormation.matchFormationId,
      idId: mFormation.idId
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
    const mFormation = this.createFromForm();
    if (mFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.mFormationService.update(mFormation));
    } else {
      this.subscribeToSaveResponse(this.mFormationService.create(mFormation));
    }
  }

  private createFromForm(): IMFormation {
    const entity = {
      ...new MFormation(),
      id: this.editForm.get(['id']).value,
      effectValue: this.editForm.get(['effectValue']).value,
      effectProbability: this.editForm.get(['effectProbability']).value,
      formationArrangementFw: this.editForm.get(['formationArrangementFw']).value,
      formationArrangementOmf: this.editForm.get(['formationArrangementOmf']).value,
      formationArrangementDmf: this.editForm.get(['formationArrangementDmf']).value,
      formationArrangementDf: this.editForm.get(['formationArrangementDf']).value,
      effectId: this.editForm.get(['effectId']).value,
      description: this.editForm.get(['description']).value,
      shortDescription: this.editForm.get(['shortDescription']).value,
      name: this.editForm.get(['name']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      startingUniformNos: this.editForm.get(['startingUniformNos']).value,
      subUniformNos: this.editForm.get(['subUniformNos']).value,
      exType: this.editForm.get(['exType']).value,
      matchFormationId: this.editForm.get(['matchFormationId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMFormation>>) {
    result.subscribe((res: HttpResponse<IMFormation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
