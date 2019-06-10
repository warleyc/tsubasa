import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCardIllustAssets, MCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';
import { MCardIllustAssetsService } from './m-card-illust-assets.service';

@Component({
  selector: 'jhi-m-card-illust-assets-update',
  templateUrl: './m-card-illust-assets-update.component.html'
})
export class MCardIllustAssetsUpdateComponent implements OnInit {
  mCardIllustAssets: IMCardIllustAssets;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    assetName: [null, [Validators.required]],
    subAssetName: [],
    offset: [],
    backgroundAssetName: [null, [Validators.required]],
    decorationAssetName: [],
    effectAssetName: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCardIllustAssetsService: MCardIllustAssetsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCardIllustAssets }) => {
      this.updateForm(mCardIllustAssets);
      this.mCardIllustAssets = mCardIllustAssets;
    });
  }

  updateForm(mCardIllustAssets: IMCardIllustAssets) {
    this.editForm.patchValue({
      id: mCardIllustAssets.id,
      assetName: mCardIllustAssets.assetName,
      subAssetName: mCardIllustAssets.subAssetName,
      offset: mCardIllustAssets.offset,
      backgroundAssetName: mCardIllustAssets.backgroundAssetName,
      decorationAssetName: mCardIllustAssets.decorationAssetName,
      effectAssetName: mCardIllustAssets.effectAssetName
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
    const mCardIllustAssets = this.createFromForm();
    if (mCardIllustAssets.id !== undefined) {
      this.subscribeToSaveResponse(this.mCardIllustAssetsService.update(mCardIllustAssets));
    } else {
      this.subscribeToSaveResponse(this.mCardIllustAssetsService.create(mCardIllustAssets));
    }
  }

  private createFromForm(): IMCardIllustAssets {
    const entity = {
      ...new MCardIllustAssets(),
      id: this.editForm.get(['id']).value,
      assetName: this.editForm.get(['assetName']).value,
      subAssetName: this.editForm.get(['subAssetName']).value,
      offset: this.editForm.get(['offset']).value,
      backgroundAssetName: this.editForm.get(['backgroundAssetName']).value,
      decorationAssetName: this.editForm.get(['decorationAssetName']).value,
      effectAssetName: this.editForm.get(['effectAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCardIllustAssets>>) {
    result.subscribe((res: HttpResponse<IMCardIllustAssets>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
