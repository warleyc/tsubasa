import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMPvpGradeRequirement, MPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';
import { MPvpGradeRequirementService } from './m-pvp-grade-requirement.service';

@Component({
  selector: 'jhi-m-pvp-grade-requirement-update',
  templateUrl: './m-pvp-grade-requirement-update.component.html'
})
export class MPvpGradeRequirementUpdateComponent implements OnInit {
  mPvpGradeRequirement: IMPvpGradeRequirement;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    upDescription: [null, [Validators.required]],
    downDescription: [null, [Validators.required]],
    targetType: [null, [Validators.required]],
    targetWave: [null, [Validators.required]],
    targetLowerGrade: [null, [Validators.required]],
    targetPoint: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mPvpGradeRequirementService: MPvpGradeRequirementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPvpGradeRequirement }) => {
      this.updateForm(mPvpGradeRequirement);
      this.mPvpGradeRequirement = mPvpGradeRequirement;
    });
  }

  updateForm(mPvpGradeRequirement: IMPvpGradeRequirement) {
    this.editForm.patchValue({
      id: mPvpGradeRequirement.id,
      upDescription: mPvpGradeRequirement.upDescription,
      downDescription: mPvpGradeRequirement.downDescription,
      targetType: mPvpGradeRequirement.targetType,
      targetWave: mPvpGradeRequirement.targetWave,
      targetLowerGrade: mPvpGradeRequirement.targetLowerGrade,
      targetPoint: mPvpGradeRequirement.targetPoint
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
    const mPvpGradeRequirement = this.createFromForm();
    if (mPvpGradeRequirement.id !== undefined) {
      this.subscribeToSaveResponse(this.mPvpGradeRequirementService.update(mPvpGradeRequirement));
    } else {
      this.subscribeToSaveResponse(this.mPvpGradeRequirementService.create(mPvpGradeRequirement));
    }
  }

  private createFromForm(): IMPvpGradeRequirement {
    const entity = {
      ...new MPvpGradeRequirement(),
      id: this.editForm.get(['id']).value,
      upDescription: this.editForm.get(['upDescription']).value,
      downDescription: this.editForm.get(['downDescription']).value,
      targetType: this.editForm.get(['targetType']).value,
      targetWave: this.editForm.get(['targetWave']).value,
      targetLowerGrade: this.editForm.get(['targetLowerGrade']).value,
      targetPoint: this.editForm.get(['targetPoint']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPvpGradeRequirement>>) {
    result.subscribe((res: HttpResponse<IMPvpGradeRequirement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
