import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRenditionTradeSign, MGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';
import { MGachaRenditionTradeSignService } from './m-gacha-rendition-trade-sign.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trade-sign-update',
  templateUrl: './m-gacha-rendition-trade-sign-update.component.html'
})
export class MGachaRenditionTradeSignUpdateComponent implements OnInit {
  mGachaRenditionTradeSign: IMGachaRenditionTradeSign;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    isSsr: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    signTextureName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionTradeSignService: MGachaRenditionTradeSignService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionTradeSign }) => {
      this.updateForm(mGachaRenditionTradeSign);
      this.mGachaRenditionTradeSign = mGachaRenditionTradeSign;
    });
  }

  updateForm(mGachaRenditionTradeSign: IMGachaRenditionTradeSign) {
    this.editForm.patchValue({
      id: mGachaRenditionTradeSign.id,
      renditionId: mGachaRenditionTradeSign.renditionId,
      isSsr: mGachaRenditionTradeSign.isSsr,
      weight: mGachaRenditionTradeSign.weight,
      signTextureName: mGachaRenditionTradeSign.signTextureName
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
    const mGachaRenditionTradeSign = this.createFromForm();
    if (mGachaRenditionTradeSign.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionTradeSignService.update(mGachaRenditionTradeSign));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionTradeSignService.create(mGachaRenditionTradeSign));
    }
  }

  private createFromForm(): IMGachaRenditionTradeSign {
    const entity = {
      ...new MGachaRenditionTradeSign(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      isSsr: this.editForm.get(['isSsr']).value,
      weight: this.editForm.get(['weight']).value,
      signTextureName: this.editForm.get(['signTextureName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionTradeSign>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionTradeSign>) => this.onSaveSuccess(),
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
