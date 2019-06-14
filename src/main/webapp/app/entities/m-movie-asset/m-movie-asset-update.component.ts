import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMovieAsset, MMovieAsset } from 'app/shared/model/m-movie-asset.model';
import { MMovieAssetService } from './m-movie-asset.service';

@Component({
  selector: 'jhi-m-movie-asset-update',
  templateUrl: './m-movie-asset-update.component.html'
})
export class MMovieAssetUpdateComponent implements OnInit {
  mMovieAsset: IMMovieAsset;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    lang: [null, [Validators.required]],
    name: [null, [Validators.required]],
    size: [null, [Validators.required]],
    version: [null, [Validators.required]],
    type: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMovieAssetService: MMovieAssetService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMovieAsset }) => {
      this.updateForm(mMovieAsset);
      this.mMovieAsset = mMovieAsset;
    });
  }

  updateForm(mMovieAsset: IMMovieAsset) {
    this.editForm.patchValue({
      id: mMovieAsset.id,
      lang: mMovieAsset.lang,
      name: mMovieAsset.name,
      size: mMovieAsset.size,
      version: mMovieAsset.version,
      type: mMovieAsset.type
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
    const mMovieAsset = this.createFromForm();
    if (mMovieAsset.id !== undefined) {
      this.subscribeToSaveResponse(this.mMovieAssetService.update(mMovieAsset));
    } else {
      this.subscribeToSaveResponse(this.mMovieAssetService.create(mMovieAsset));
    }
  }

  private createFromForm(): IMMovieAsset {
    const entity = {
      ...new MMovieAsset(),
      id: this.editForm.get(['id']).value,
      lang: this.editForm.get(['lang']).value,
      name: this.editForm.get(['name']).value,
      size: this.editForm.get(['size']).value,
      version: this.editForm.get(['version']).value,
      type: this.editForm.get(['type']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMovieAsset>>) {
    result.subscribe((res: HttpResponse<IMMovieAsset>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
