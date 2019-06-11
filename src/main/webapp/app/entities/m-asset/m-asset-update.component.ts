import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAsset, MAsset } from 'app/shared/model/m-asset.model';
import { MAssetService } from './m-asset.service';

@Component({
  selector: 'jhi-m-asset-update',
  templateUrl: './m-asset-update.component.html'
})
export class MAssetUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    assetBundleName: [null, [Validators.required]],
    tag: [null, [Validators.required]],
    dependencies: [null, [Validators.required]],
    i18n: [null, [Validators.required]],
    platform: [null, [Validators.required]],
    packName: [null, [Validators.required]],
    head: [null, [Validators.required]],
    size: [null, [Validators.required]],
    key1: [null, [Validators.required]],
    key2: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mAssetService: MAssetService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAsset }) => {
      this.updateForm(mAsset);
    });
  }

  updateForm(mAsset: IMAsset) {
    this.editForm.patchValue({
      id: mAsset.id,
      assetBundleName: mAsset.assetBundleName,
      tag: mAsset.tag,
      dependencies: mAsset.dependencies,
      i18n: mAsset.i18n,
      platform: mAsset.platform,
      packName: mAsset.packName,
      head: mAsset.head,
      size: mAsset.size,
      key1: mAsset.key1,
      key2: mAsset.key2
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
    const mAsset = this.createFromForm();
    if (mAsset.id !== undefined) {
      this.subscribeToSaveResponse(this.mAssetService.update(mAsset));
    } else {
      this.subscribeToSaveResponse(this.mAssetService.create(mAsset));
    }
  }

  private createFromForm(): IMAsset {
    const entity = {
      ...new MAsset(),
      id: this.editForm.get(['id']).value,
      assetBundleName: this.editForm.get(['assetBundleName']).value,
      tag: this.editForm.get(['tag']).value,
      dependencies: this.editForm.get(['dependencies']).value,
      i18n: this.editForm.get(['i18n']).value,
      platform: this.editForm.get(['platform']).value,
      packName: this.editForm.get(['packName']).value,
      head: this.editForm.get(['head']).value,
      size: this.editForm.get(['size']).value,
      key1: this.editForm.get(['key1']).value,
      key2: this.editForm.get(['key2']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAsset>>) {
    result.subscribe((res: HttpResponse<IMAsset>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
