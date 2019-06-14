import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMModelUniformUp, MModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';
import { MModelUniformUpService } from './m-model-uniform-up.service';

@Component({
  selector: 'jhi-m-model-uniform-up-update',
  templateUrl: './m-model-uniform-up-update.component.html'
})
export class MModelUniformUpUpdateComponent implements OnInit {
  mModelUniformUp: IMModelUniformUp;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    uniformUpId: [null, [Validators.required]],
    defaultDressingType: [null, [Validators.required]],
    uniformModel: [null, [Validators.required]],
    uniformTexture: [null, [Validators.required]],
    uniformNoTexture: [null, [Validators.required]],
    defaultColor: [],
    uniformNoColor: [null, [Validators.required]],
    numberChest: [null, [Validators.required]],
    numberBelly: [null, [Validators.required]],
    numberBack: [null, [Validators.required]],
    uniformNoPositionX: [],
    uniformNoPositionY: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mModelUniformUpService: MModelUniformUpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelUniformUp }) => {
      this.updateForm(mModelUniformUp);
      this.mModelUniformUp = mModelUniformUp;
    });
  }

  updateForm(mModelUniformUp: IMModelUniformUp) {
    this.editForm.patchValue({
      id: mModelUniformUp.id,
      uniformUpId: mModelUniformUp.uniformUpId,
      defaultDressingType: mModelUniformUp.defaultDressingType,
      uniformModel: mModelUniformUp.uniformModel,
      uniformTexture: mModelUniformUp.uniformTexture,
      uniformNoTexture: mModelUniformUp.uniformNoTexture,
      defaultColor: mModelUniformUp.defaultColor,
      uniformNoColor: mModelUniformUp.uniformNoColor,
      numberChest: mModelUniformUp.numberChest,
      numberBelly: mModelUniformUp.numberBelly,
      numberBack: mModelUniformUp.numberBack,
      uniformNoPositionX: mModelUniformUp.uniformNoPositionX,
      uniformNoPositionY: mModelUniformUp.uniformNoPositionY
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
    const mModelUniformUp = this.createFromForm();
    if (mModelUniformUp.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelUniformUpService.update(mModelUniformUp));
    } else {
      this.subscribeToSaveResponse(this.mModelUniformUpService.create(mModelUniformUp));
    }
  }

  private createFromForm(): IMModelUniformUp {
    const entity = {
      ...new MModelUniformUp(),
      id: this.editForm.get(['id']).value,
      uniformUpId: this.editForm.get(['uniformUpId']).value,
      defaultDressingType: this.editForm.get(['defaultDressingType']).value,
      uniformModel: this.editForm.get(['uniformModel']).value,
      uniformTexture: this.editForm.get(['uniformTexture']).value,
      uniformNoTexture: this.editForm.get(['uniformNoTexture']).value,
      defaultColor: this.editForm.get(['defaultColor']).value,
      uniformNoColor: this.editForm.get(['uniformNoColor']).value,
      numberChest: this.editForm.get(['numberChest']).value,
      numberBelly: this.editForm.get(['numberBelly']).value,
      numberBack: this.editForm.get(['numberBack']).value,
      uniformNoPositionX: this.editForm.get(['uniformNoPositionX']).value,
      uniformNoPositionY: this.editForm.get(['uniformNoPositionY']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelUniformUp>>) {
    result.subscribe((res: HttpResponse<IMModelUniformUp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
