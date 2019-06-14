import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMModelUniformBottom, MModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';
import { MModelUniformBottomService } from './m-model-uniform-bottom.service';

@Component({
  selector: 'jhi-m-model-uniform-bottom-update',
  templateUrl: './m-model-uniform-bottom-update.component.html'
})
export class MModelUniformBottomUpdateComponent implements OnInit {
  mModelUniformBottom: IMModelUniformBottom;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    uniformBottomId: [null, [Validators.required]],
    defaultDressingType: [null, [Validators.required]],
    uniformModel: [null, [Validators.required]],
    uniformTexture: [null, [Validators.required]],
    uniformNoTexture: [null, [Validators.required]],
    defaultColor: [],
    uniformNoColor: [null, [Validators.required]],
    numberRightLeg: [null, [Validators.required]],
    numberLeftLeg: [null, [Validators.required]],
    uniformNoPositionX: [],
    uniformNoPositionY: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mModelUniformBottomService: MModelUniformBottomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelUniformBottom }) => {
      this.updateForm(mModelUniformBottom);
      this.mModelUniformBottom = mModelUniformBottom;
    });
  }

  updateForm(mModelUniformBottom: IMModelUniformBottom) {
    this.editForm.patchValue({
      id: mModelUniformBottom.id,
      uniformBottomId: mModelUniformBottom.uniformBottomId,
      defaultDressingType: mModelUniformBottom.defaultDressingType,
      uniformModel: mModelUniformBottom.uniformModel,
      uniformTexture: mModelUniformBottom.uniformTexture,
      uniformNoTexture: mModelUniformBottom.uniformNoTexture,
      defaultColor: mModelUniformBottom.defaultColor,
      uniformNoColor: mModelUniformBottom.uniformNoColor,
      numberRightLeg: mModelUniformBottom.numberRightLeg,
      numberLeftLeg: mModelUniformBottom.numberLeftLeg,
      uniformNoPositionX: mModelUniformBottom.uniformNoPositionX,
      uniformNoPositionY: mModelUniformBottom.uniformNoPositionY
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
    const mModelUniformBottom = this.createFromForm();
    if (mModelUniformBottom.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelUniformBottomService.update(mModelUniformBottom));
    } else {
      this.subscribeToSaveResponse(this.mModelUniformBottomService.create(mModelUniformBottom));
    }
  }

  private createFromForm(): IMModelUniformBottom {
    const entity = {
      ...new MModelUniformBottom(),
      id: this.editForm.get(['id']).value,
      uniformBottomId: this.editForm.get(['uniformBottomId']).value,
      defaultDressingType: this.editForm.get(['defaultDressingType']).value,
      uniformModel: this.editForm.get(['uniformModel']).value,
      uniformTexture: this.editForm.get(['uniformTexture']).value,
      uniformNoTexture: this.editForm.get(['uniformNoTexture']).value,
      defaultColor: this.editForm.get(['defaultColor']).value,
      uniformNoColor: this.editForm.get(['uniformNoColor']).value,
      numberRightLeg: this.editForm.get(['numberRightLeg']).value,
      numberLeftLeg: this.editForm.get(['numberLeftLeg']).value,
      uniformNoPositionX: this.editForm.get(['uniformNoPositionX']).value,
      uniformNoPositionY: this.editForm.get(['uniformNoPositionY']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelUniformBottom>>) {
    result.subscribe((res: HttpResponse<IMModelUniformBottom>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
