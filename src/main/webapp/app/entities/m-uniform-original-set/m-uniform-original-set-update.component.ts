import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMUniformOriginalSet, MUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';
import { MUniformOriginalSetService } from './m-uniform-original-set.service';

@Component({
  selector: 'jhi-m-uniform-original-set-update',
  templateUrl: './m-uniform-original-set-update.component.html'
})
export class MUniformOriginalSetUpdateComponent implements OnInit {
  mUniformOriginalSet: IMUniformOriginalSet;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    menuName: [null, [Validators.required]],
    upModelId: [null, [Validators.required]],
    bottomModelId: [null, [Validators.required]],
    thumbnailAssetName: [null, [Validators.required]],
    uniformType: [null, [Validators.required]],
    isDefault: [null, [Validators.required]],
    orderNum: [null, [Validators.required]],
    description: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mUniformOriginalSetService: MUniformOriginalSetService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mUniformOriginalSet }) => {
      this.updateForm(mUniformOriginalSet);
      this.mUniformOriginalSet = mUniformOriginalSet;
    });
  }

  updateForm(mUniformOriginalSet: IMUniformOriginalSet) {
    this.editForm.patchValue({
      id: mUniformOriginalSet.id,
      name: mUniformOriginalSet.name,
      shortName: mUniformOriginalSet.shortName,
      menuName: mUniformOriginalSet.menuName,
      upModelId: mUniformOriginalSet.upModelId,
      bottomModelId: mUniformOriginalSet.bottomModelId,
      thumbnailAssetName: mUniformOriginalSet.thumbnailAssetName,
      uniformType: mUniformOriginalSet.uniformType,
      isDefault: mUniformOriginalSet.isDefault,
      orderNum: mUniformOriginalSet.orderNum,
      description: mUniformOriginalSet.description
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
    const mUniformOriginalSet = this.createFromForm();
    if (mUniformOriginalSet.id !== undefined) {
      this.subscribeToSaveResponse(this.mUniformOriginalSetService.update(mUniformOriginalSet));
    } else {
      this.subscribeToSaveResponse(this.mUniformOriginalSetService.create(mUniformOriginalSet));
    }
  }

  private createFromForm(): IMUniformOriginalSet {
    const entity = {
      ...new MUniformOriginalSet(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      menuName: this.editForm.get(['menuName']).value,
      upModelId: this.editForm.get(['upModelId']).value,
      bottomModelId: this.editForm.get(['bottomModelId']).value,
      thumbnailAssetName: this.editForm.get(['thumbnailAssetName']).value,
      uniformType: this.editForm.get(['uniformType']).value,
      isDefault: this.editForm.get(['isDefault']).value,
      orderNum: this.editForm.get(['orderNum']).value,
      description: this.editForm.get(['description']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMUniformOriginalSet>>) {
    result.subscribe((res: HttpResponse<IMUniformOriginalSet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
