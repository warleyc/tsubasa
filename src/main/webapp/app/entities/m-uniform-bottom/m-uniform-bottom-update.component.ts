import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMUniformBottom, MUniformBottom } from 'app/shared/model/m-uniform-bottom.model';
import { MUniformBottomService } from './m-uniform-bottom.service';

@Component({
  selector: 'jhi-m-uniform-bottom-update',
  templateUrl: './m-uniform-bottom-update.component.html'
})
export class MUniformBottomUpdateComponent implements OnInit {
  mUniformBottom: IMUniformBottom;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    menuName: [null, [Validators.required]],
    modelId: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    uniformType: [null, [Validators.required]],
    isDefault: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mUniformBottomService: MUniformBottomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mUniformBottom }) => {
      this.updateForm(mUniformBottom);
      this.mUniformBottom = mUniformBottom;
    });
  }

  updateForm(mUniformBottom: IMUniformBottom) {
    this.editForm.patchValue({
      id: mUniformBottom.id,
      name: mUniformBottom.name,
      shortName: mUniformBottom.shortName,
      menuName: mUniformBottom.menuName,
      modelId: mUniformBottom.modelId,
      thumbnailAssetName: mUniformBottom.thumbnailAssetName,
      uniformType: mUniformBottom.uniformType,
      isDefault: mUniformBottom.isDefault,
      orderNum: mUniformBottom.orderNum,
      description: mUniformBottom.description
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
    const mUniformBottom = this.createFromForm();
    if (mUniformBottom.id !== undefined) {
      this.subscribeToSaveResponse(this.mUniformBottomService.update(mUniformBottom));
    } else {
      this.subscribeToSaveResponse(this.mUniformBottomService.create(mUniformBottom));
    }
  }

  private createFromForm(): IMUniformBottom {
    const entity = {
      ...new MUniformBottom(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      menuName: this.editForm.get(['menuName']).value,
      modelId: this.editForm.get(['modelId']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      uniformType: this.editForm.get(['uniformType']).value,
      isDefault: this.editForm.get(['isDefault']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMUniformBottom>>) {
    result.subscribe((res: HttpResponse<IMUniformBottom>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
