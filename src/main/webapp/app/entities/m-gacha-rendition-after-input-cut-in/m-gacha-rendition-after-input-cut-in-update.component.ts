import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import {
  IMGachaRenditionAfterInputCutIn,
  MGachaRenditionAfterInputCutIn
} from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';
import { MGachaRenditionAfterInputCutInService } from './m-gacha-rendition-after-input-cut-in.service';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-update',
  templateUrl: './m-gacha-rendition-after-input-cut-in-update.component.html'
})
export class MGachaRenditionAfterInputCutInUpdateComponent implements OnInit {
  mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    cutInBgPrefabName: [null, [Validators.required]],
    seStartCutIn: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionAfterInputCutInService: MGachaRenditionAfterInputCutInService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutIn }) => {
      this.updateForm(mGachaRenditionAfterInputCutIn);
      this.mGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutIn;
    });
  }

  updateForm(mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn) {
    this.editForm.patchValue({
      id: mGachaRenditionAfterInputCutIn.id,
      renditionId: mGachaRenditionAfterInputCutIn.renditionId,
      isSsr: mGachaRenditionAfterInputCutIn.isSsr,
      weight: mGachaRenditionAfterInputCutIn.weight,
      cutInBgPrefabName: mGachaRenditionAfterInputCutIn.cutInBgPrefabName,
      seStartCutIn: mGachaRenditionAfterInputCutIn.seStartCutIn
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
    const mGachaRenditionAfterInputCutIn = this.createFromForm();
    if (mGachaRenditionAfterInputCutIn.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionAfterInputCutInService.update(mGachaRenditionAfterInputCutIn));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionAfterInputCutInService.create(mGachaRenditionAfterInputCutIn));
    }
  }

  private createFromForm(): IMGachaRenditionAfterInputCutIn {
    const entity = {
      ...new MGachaRenditionAfterInputCutIn(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      cutInBgPrefabName: this.editForm.get(['cutInBgPrefabName']).value,
      seStartCutIn: this.editForm.get(['seStartCutIn']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionAfterInputCutIn>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionAfterInputCutIn>) => this.onSaveSuccess(),
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
