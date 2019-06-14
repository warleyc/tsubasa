import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMPvpGrade, MPvpGrade } from 'app/shared/model/m-pvp-grade.model';
import { MPvpGradeService } from './m-pvp-grade.service';

@Component({
  selector: 'jhi-m-pvp-grade-update',
  templateUrl: './m-pvp-grade-update.component.html'
})
export class MPvpGradeUpdateComponent implements OnInit {
  mPvpGrade: IMPvpGrade;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    waveId: [null, [Validators.required]],
    grade: [null, [Validators.required]],
    isHigher: [null, [Validators.required]],
    isLower: [null, [Validators.required]],
    gradeName: [null, [Validators.required]],
    look: [null, [Validators.required]],
    upRequirementId: [],
    downRequirementId: [],
    winPoint: [null, [Validators.required]],
    losePoint: [null, [Validators.required]],
    gradeUpSerif: [null, [Validators.required]],
    gradeDownSerif: [null, [Validators.required]],
    gradeUpCharaAssetName: [null, [Validators.required]],
    gradeDownCharaAssetName: [null, [Validators.required]],
    gradeUpVoiceCharaId: [null, [Validators.required]],
    gradeDownVoiceCharaId: [null, [Validators.required]],
    totalParameterRangeUpper: [null, [Validators.required]],
    totalParameterRangeLower: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mPvpGradeService: MPvpGradeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpGrade }) => {
      this.updateForm(mPvpGrade);
      this.mPvpGrade = mPvpGrade;
    });
  }

  updateForm(mPvpGrade: IMPvpGrade) {
    this.editForm.patchValue({
      id: mPvpGrade.id,
      waveId: mPvpGrade.waveId,
      grade: mPvpGrade.grade,
      isHigher: mPvpGrade.isHigher,
      isLower: mPvpGrade.isLower,
      gradeName: mPvpGrade.gradeName,
      look: mPvpGrade.look,
      upRequirementId: mPvpGrade.upRequirementId,
      downRequirementId: mPvpGrade.downRequirementId,
      winPoint: mPvpGrade.winPoint,
      losePoint: mPvpGrade.losePoint,
      gradeUpSerif: mPvpGrade.gradeUpSerif,
      gradeDownSerif: mPvpGrade.gradeDownSerif,
      gradeUpCharaAssetName: mPvpGrade.gradeUpCharaAssetName,
      gradeDownCharaAssetName: mPvpGrade.gradeDownCharaAssetName,
      gradeUpVoiceCharaId: mPvpGrade.gradeUpVoiceCharaId,
      gradeDownVoiceCharaId: mPvpGrade.gradeDownVoiceCharaId,
      totalParameterRangeUpper: mPvpGrade.totalParameterRangeUpper,
      totalParameterRangeLower: mPvpGrade.totalParameterRangeLower
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
    const mPvpGrade = this.createFromForm();
    if (mPvpGrade.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpGradeService.update(mPvpGrade));
    } else {
      this.subscribeToSaveResponse(this.mPvpGradeService.create(mPvpGrade));
    }
  }

  private createFromForm(): IMPvpGrade {
    const entity = {
      ...new MPvpGrade(),
      id: this.editForm.get(['id']).value,
      waveId: this.editForm.get(['waveId']).value,
      grade: this.editForm.get(['grade']).value,
      isHigher: this.editForm.get(['isHigher']).value,
      isLower: this.editForm.get(['isLower']).value,
      gradeName: this.editForm.get(['gradeName']).value,
      look: this.editForm.get(['look']).value,
      upRequirementId: this.editForm.get(['upRequirementId']).value,
      downRequirementId: this.editForm.get(['downRequirementId']).value,
      winPoint: this.editForm.get(['winPoint']).value,
      losePoint: this.editForm.get(['losePoint']).value,
      gradeUpSerif: this.editForm.get(['gradeUpSerif']).value,
      gradeDownSerif: this.editForm.get(['gradeDownSerif']).value,
      gradeUpCharaAssetName: this.editForm.get(['gradeUpCharaAssetName']).value,
      gradeDownCharaAssetName: this.editForm.get(['gradeDownCharaAssetName']).value,
      gradeUpVoiceCharaId: this.editForm.get(['gradeUpVoiceCharaId']).value,
      gradeDownVoiceCharaId: this.editForm.get(['gradeDownVoiceCharaId']).value,
      totalParameterRangeUpper: this.editForm.get(['totalParameterRangeUpper']).value,
      totalParameterRangeLower: this.editForm.get(['totalParameterRangeLower']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpGrade>>) {
    result.subscribe((res: HttpResponse<IMPvpGrade>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
