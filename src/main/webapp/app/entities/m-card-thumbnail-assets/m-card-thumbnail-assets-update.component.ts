import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCardThumbnailAssets, MCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';
import { MCardThumbnailAssetsService } from './m-card-thumbnail-assets.service';

@Component({
  selector: 'jhi-m-card-thumbnail-assets-update',
  templateUrl: './m-card-thumbnail-assets-update.component.html'
})
export class MCardThumbnailAssetsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    thumbnailAssetName: [null, [Validators.required]],
    thumbnailFrameName: [null, [Validators.required]],
    thumbnailBackgoundAssetName: [],
    thumbnailEffectAssetName: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCardThumbnailAssetsService: MCardThumbnailAssetsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCardThumbnailAssets }) => {
      this.updateForm(mCardThumbnailAssets);
    });
  }

  updateForm(mCardThumbnailAssets: IMCardThumbnailAssets) {
    this.editForm.patchValue({
      id: mCardThumbnailAssets.id,
      thumbnailAssetName: mCardThumbnailAssets.thumbnailAssetName,
      thumbnailFrameName: mCardThumbnailAssets.thumbnailFrameName,
      thumbnailBackgoundAssetName: mCardThumbnailAssets.thumbnailBackgoundAssetName,
      thumbnailEffectAssetName: mCardThumbnailAssets.thumbnailEffectAssetName
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
    const mCardThumbnailAssets = this.createFromForm();
    if (mCardThumbnailAssets.id !== undefined) {
      this.subscribeToSaveResponse(this.mCardThumbnailAssetsService.update(mCardThumbnailAssets));
    } else {
      this.subscribeToSaveResponse(this.mCardThumbnailAssetsService.create(mCardThumbnailAssets));
    }
  }

  private createFromForm(): IMCardThumbnailAssets {
    const entity = {
      ...new MCardThumbnailAssets(),
      id: this.editForm.get(['id']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      thumbnailFrameName: this.editForm.get(['thumbnailFrameName']).value,
      thumbnailBackgoundAssetName: this.editForm.get(['thumbnailBackgoundAssetName']).value,
      thumbnailEffectAssetName: this.editForm.get(['thumbnailEffectAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCardThumbnailAssets>>) {
    result.subscribe((res: HttpResponse<IMCardThumbnailAssets>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
