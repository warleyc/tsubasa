import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMedal, MMedal } from 'app/shared/model/m-medal.model';
import { MMedalService } from './m-medal.service';

@Component({
  selector: 'jhi-m-medal-update',
  templateUrl: './m-medal-update.component.html'
})
export class MMedalUpdateComponent implements OnInit {
  mMedal: IMMedal;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    medalType: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    maxAmount: [null, [Validators.required]],
    iconAssetName: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMedalService: MMedalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMedal }) => {
      this.updateForm(mMedal);
      this.mMedal = mMedal;
    });
  }

  updateForm(mMedal: IMMedal) {
    this.editForm.patchValue({
      id: mMedal.id,
      medalType: mMedal.medalType,
      name: mMedal.name,
      description: mMedal.description,
      maxAmount: mMedal.maxAmount,
      iconAssetName: mMedal.iconAssetName,
      thumbnailAssetName: mMedal.thumbnailAssetName
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
    const mMedal = this.createFromForm();
    if (mMedal.id !== undefined) {
      this.subscribeToSaveResponse(this.mMedalService.update(mMedal));
    } else {
      this.subscribeToSaveResponse(this.mMedalService.create(mMedal));
    }
  }

  private createFromForm(): IMMedal {
    const entity = {
      ...new MMedal(),
      id: this.editForm.get(['id']).value,
      medalType: this.editForm.get(['medalType']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      maxAmount: this.editForm.get(['maxAmount']).value,
      iconAssetName: this.editForm.get(['iconAssetName']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMedal>>) {
    result.subscribe((res: HttpResponse<IMMedal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
