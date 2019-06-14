import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLuck, MLuck } from 'app/shared/model/m-luck.model';
import { MLuckService } from './m-luck.service';

@Component({
  selector: 'jhi-m-luck-update',
  templateUrl: './m-luck-update.component.html'
})
export class MLuckUpdateComponent implements OnInit {
  mLuck: IMLuck;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    targetAttribute: [],
    targetCharacterGroupId: [],
    targetTeamGroupId: [],
    targetNationalityGroupId: [],
    luckRateGroupId: [null, [Validators.required]],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLuckService: MLuckService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLuck }) => {
      this.updateForm(mLuck);
      this.mLuck = mLuck;
    });
  }

  updateForm(mLuck: IMLuck) {
    this.editForm.patchValue({
      id: mLuck.id,
      targetAttribute: mLuck.targetAttribute,
      targetCharacterGroupId: mLuck.targetCharacterGroupId,
      targetTeamGroupId: mLuck.targetTeamGroupId,
      targetNationalityGroupId: mLuck.targetNationalityGroupId,
      luckRateGroupId: mLuck.luckRateGroupId,
      description: mLuck.description
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
    const mLuck = this.createFromForm();
    if (mLuck.id !== undefined) {
      this.subscribeToSaveResponse(this.mLuckService.update(mLuck));
    } else {
      this.subscribeToSaveResponse(this.mLuckService.create(mLuck));
    }
  }

  private createFromForm(): IMLuck {
    const entity = {
      ...new MLuck(),
      id: this.editForm.get(['id']).value,
      targetAttribute: this.editForm.get(['targetAttribute']).value,
      targetCharacterGroupId: this.editForm.get(['targetCharacterGroupId']).value,
      targetTeamGroupId: this.editForm.get(['targetTeamGroupId']).value,
      targetNationalityGroupId: this.editForm.get(['targetNationalityGroupId']).value,
      luckRateGroupId: this.editForm.get(['luckRateGroupId']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLuck>>) {
    result.subscribe((res: HttpResponse<IMLuck>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
