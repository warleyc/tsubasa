import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGachaRenditionExtraCutin, MGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';
import { MGachaRenditionExtraCutinService } from './m-gacha-rendition-extra-cutin.service';

@Component({
  selector: 'jhi-m-gacha-rendition-extra-cutin-update',
  templateUrl: './m-gacha-rendition-extra-cutin-update.component.html'
})
export class MGachaRenditionExtraCutinUpdateComponent implements OnInit {
  mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    renditionId: [null, [Validators.required]],
    mainPrefabName: [null, [Validators.required]],
    voiceStartCutIn: [null, [Validators.required]],
    serif: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGachaRenditionExtraCutinService: MGachaRenditionExtraCutinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGachaRenditionExtraCutin }) => {
      this.updateForm(mGachaRenditionExtraCutin);
      this.mGachaRenditionExtraCutin = mGachaRenditionExtraCutin;
    });
  }

  updateForm(mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin) {
    this.editForm.patchValue({
      id: mGachaRenditionExtraCutin.id,
      renditionId: mGachaRenditionExtraCutin.renditionId,
      mainPrefabName: mGachaRenditionExtraCutin.mainPrefabName,
      voiceStartCutIn: mGachaRenditionExtraCutin.voiceStartCutIn,
      serif: mGachaRenditionExtraCutin.serif
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
    const mGachaRenditionExtraCutin = this.createFromForm();
    if (mGachaRenditionExtraCutin.id !== undefined) {
      this.subscribeToSaveResponse(this.mGachaRenditionExtraCutinService.update(mGachaRenditionExtraCutin));
    } else {
      this.subscribeToSaveResponse(this.mGachaRenditionExtraCutinService.create(mGachaRenditionExtraCutin));
    }
  }

  private createFromForm(): IMGachaRenditionExtraCutin {
    const entity = {
      ...new MGachaRenditionExtraCutin(),
      id: this.editForm.get(['id']).value,
      renditionId: this.editForm.get(['renditionId']).value,
      mainPrefabName: this.editForm.get(['mainPrefabName']).value,
      voiceStartCutIn: this.editForm.get(['voiceStartCutIn']).value,
      serif: this.editForm.get(['serif']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGachaRenditionExtraCutin>>) {
    result.subscribe(
      (res: HttpResponse<IMGachaRenditionExtraCutin>) => this.onSaveSuccess(),
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
