import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMGachaRenditionAfterInputCutInTextColor,
  MGachaRenditionAfterInputCutInTextColor
} from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';
import { MGachaRenditionAfterInputCutInTextColorService } from './m-gacha-rendition-after-input-cut-in-text-color.service';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-text-color-update',
  templateUrl: './m-gacha-rendition-after-input-cut-in-text-color-update.component.html'
})
export class MGachaRenditionAfterInputCutInTextColorUpdateComponent implements OnInit {
  mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    color: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionAfterInputCutInTextColorService: MGachaRenditionAfterInputCutInTextColorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutInTextColor }) => {
      this.updateForm(mGachaRenditionAfterInputCutInTextColor);
      this.mGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColor;
    });
  }

  updateForm(mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor) {
    this.editForm.patchValue({
      id: mGachaRenditionAfterInputCutInTextColor.id,
      isSsr: mGachaRenditionAfterInputCutInTextColor.isSsr,
      weight: mGachaRenditionAfterInputCutInTextColor.weight,
      color: mGachaRenditionAfterInputCutInTextColor.color
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
    const mGachaRenditionAfterInputCutInTextColor = this.createFromForm();
    if (mGachaRenditionAfterInputCutInTextColor.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionAfterInputCutInTextColorService.update(mGachaRenditionAfterInputCutInTextColor));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionAfterInputCutInTextColorService.create(mGachaRenditionAfterInputCutInTextColor));
    }
  }

  private createFromForm(): IMGachaRenditionAfterInputCutInTextColor {
    const entity = {
      ...new MGachaRenditionAfterInputCutInTextColor(),
      id: this.editForm.get(['id']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      color: this.editForm.get(['color']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionAfterInputCutInTextColor>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionAfterInputCutInTextColor>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
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
