import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMPassiveEffectRange, MPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from './m-passive-effect-range.service';

@Component({
  selector: 'jhi-m-passive-effect-range-update',
  templateUrl: './m-passive-effect-range-update.component.html'
})
export class MPassiveEffectRangeUpdateComponent implements OnInit {
  mPassiveEffectRange: IMPassiveEffectRange;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    effectParamType: [null, [Validators.required]],
    targetPositionGk: [null, [Validators.required]],
    targetPositionFw: [null, [Validators.required]],
    targetPositionOmf: [null, [Validators.required]],
    targetPositionDmf: [null, [Validators.required]],
    targetPositionDf: [null, [Validators.required]],
    targetAttribute: [null, [Validators.required]],
    targetNationalityGroupId: [],
    targetTeamGroupId: [],
    targetPlayableCardGroupId: [],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mPassiveEffectRangeService: MPassiveEffectRangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPassiveEffectRange }) => {
      this.updateForm(mPassiveEffectRange);
      this.mPassiveEffectRange = mPassiveEffectRange;
    });
  }

  updateForm(mPassiveEffectRange: IMPassiveEffectRange) {
    this.editForm.patchValue({
      id: mPassiveEffectRange.id,
      name: mPassiveEffectRange.name,
      effectParamType: mPassiveEffectRange.effectParamType,
      targetPositionGk: mPassiveEffectRange.targetPositionGk,
      targetPositionFw: mPassiveEffectRange.targetPositionFw,
      targetPositionOmf: mPassiveEffectRange.targetPositionOmf,
      targetPositionDmf: mPassiveEffectRange.targetPositionDmf,
      targetPositionDf: mPassiveEffectRange.targetPositionDf,
      targetAttribute: mPassiveEffectRange.targetAttribute,
      targetNationalityGroupId: mPassiveEffectRange.targetNationalityGroupId,
      targetTeamGroupId: mPassiveEffectRange.targetTeamGroupId,
      targetPlayableCardGroupId: mPassiveEffectRange.targetPlayableCardGroupId,
      description: mPassiveEffectRange.description
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
    const mPassiveEffectRange = this.createFromForm();
    if (mPassiveEffectRange.id !== undefined) {
      this.subscribeToSaveResponse(this.mPassiveEffectRangeService.update(mPassiveEffectRange));
    } else {
      this.subscribeToSaveResponse(this.mPassiveEffectRangeService.create(mPassiveEffectRange));
    }
  }

  private createFromForm(): IMPassiveEffectRange {
    const entity = {
      ...new MPassiveEffectRange(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      effectParamType: this.editForm.get(['effectParamType']).value,
      targetPositionGk: this.editForm.get(['targetPositionGk']).value,
      targetPositionFw: this.editForm.get(['targetPositionFw']).value,
      targetPositionOmf: this.editForm.get(['targetPositionOmf']).value,
      targetPositionDmf: this.editForm.get(['targetPositionDmf']).value,
      targetPositionDf: this.editForm.get(['targetPositionDf']).value,
      targetAttribute: this.editForm.get(['targetAttribute']).value,
      targetNationalityGroupId: this.editForm.get(['targetNationalityGroupId']).value,
      targetTeamGroupId: this.editForm.get(['targetTeamGroupId']).value,
      targetPlayableCardGroupId: this.editForm.get(['targetPlayableCardGroupId']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPassiveEffectRange>>) {
    result.subscribe((res: HttpResponse<IMPassiveEffectRange>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
