import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMUniformUp, MUniformUp } from 'app/shared/model/m-uniform-up.model';
import { MUniformUpService } from './m-uniform-up.service';

@Component({
  selector: 'jhi-m-uniform-up-update',
  templateUrl: './m-uniform-up-update.component.html'
})
export class MUniformUpUpdateComponent implements OnInit {
  mUniformUp: IMUniformUp;
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
    protected mUniformUpService: MUniformUpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mUniformUp }) => {
      this.updateForm(mUniformUp);
      this.mUniformUp = mUniformUp;
    });
  }

  updateForm(mUniformUp: IMUniformUp) {
    this.editForm.patchValue({
      id: mUniformUp.id,
      name: mUniformUp.name,
      shortName: mUniformUp.shortName,
      menuName: mUniformUp.menuName,
      modelId: mUniformUp.modelId,
      thumbnailAssetName: mUniformUp.thumbnailAssetName,
      uniformType: mUniformUp.uniformType,
      isDefault: mUniformUp.isDefault,
      orderNum: mUniformUp.orderNum,
      description: mUniformUp.description
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
    const mUniformUp = this.createFromForm();
    if (mUniformUp.id !== undefined) {
      this.subscribeToSaveResponse(this.mUniformUpService.update(mUniformUp));
    } else {
      this.subscribeToSaveResponse(this.mUniformUpService.create(mUniformUp));
    }
  }

  private createFromForm(): IMUniformUp {
    const entity = {
      ...new MUniformUp(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMUniformUp>>) {
    result.subscribe((res: HttpResponse<IMUniformUp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
