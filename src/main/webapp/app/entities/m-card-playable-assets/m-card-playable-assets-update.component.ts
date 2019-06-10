import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMCardPlayableAssets, MCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';
import { MCardPlayableAssetsService } from './m-card-playable-assets.service';

@Component({
  selector: 'jhi-m-card-playable-assets-update',
  templateUrl: './m-card-playable-assets-update.component.html'
})
export class MCardPlayableAssetsUpdateComponent implements OnInit {
  mCardPlayableAssets: IMCardPlayableAssets;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    cutinImageAssetName: [null, [Validators.required]],
    soundEventSuffix: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mCardPlayableAssetsService: MCardPlayableAssetsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mCardPlayableAssets }) => {
      this.updateForm(mCardPlayableAssets);
      this.mCardPlayableAssets = mCardPlayableAssets;
    });
  }

  updateForm(mCardPlayableAssets: IMCardPlayableAssets) {
    this.editForm.patchValue({
      id: mCardPlayableAssets.id,
      cutinImageAssetName: mCardPlayableAssets.cutinImageAssetName,
      soundEventSuffix: mCardPlayableAssets.soundEventSuffix
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
    const mCardPlayableAssets = this.createFromForm();
    if (mCardPlayableAssets.id !== undefined) {
      this.subscribeToSaveResponse(this.mCardPlayableAssetsService.update(mCardPlayableAssets));
    } else {
      this.subscribeToSaveResponse(this.mCardPlayableAssetsService.create(mCardPlayableAssets));
    }
  }

  private createFromForm(): IMCardPlayableAssets {
    const entity = {
      ...new MCardPlayableAssets(),
      id: this.editForm.get(['id']).value,
      cutinImageAssetName: this.editForm.get(['cutinImageAssetName']).value,
      soundEventSuffix: this.editForm.get(['soundEventSuffix']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMCardPlayableAssets>>) {
    result.subscribe((res: HttpResponse<IMCardPlayableAssets>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
