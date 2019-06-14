import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMModelQuestStage, MModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';
import { MModelQuestStageService } from './m-model-quest-stage.service';

@Component({
  selector: 'jhi-m-model-quest-stage-update',
  templateUrl: './m-model-quest-stage-update.component.html'
})
export class MModelQuestStageUpdateComponent implements OnInit {
  mModelQuestStage: IMModelQuestStage;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    stageId: [null, [Validators.required]],
    image: [null, [Validators.required]],
    modelName: [null, [Validators.required]],
    bgmOffencing: [null, [Validators.required]],
    bgmDefencing: [null, [Validators.required]],
    bgmHurrying: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mModelQuestStageService: MModelQuestStageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mModelQuestStage }) => {
      this.updateForm(mModelQuestStage);
      this.mModelQuestStage = mModelQuestStage;
    });
  }

  updateForm(mModelQuestStage: IMModelQuestStage) {
    this.editForm.patchValue({
      id: mModelQuestStage.id,
      stageId: mModelQuestStage.stageId,
      image: mModelQuestStage.image,
      modelName: mModelQuestStage.modelName,
      bgmOffencing: mModelQuestStage.bgmOffencing,
      bgmDefencing: mModelQuestStage.bgmDefencing,
      bgmHurrying: mModelQuestStage.bgmHurrying
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
    const mModelQuestStage = this.createFromForm();
    if (mModelQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mModelQuestStageService.update(mModelQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mModelQuestStageService.create(mModelQuestStage));
    }
  }

  private createFromForm(): IMModelQuestStage {
    const entity = {
      ...new MModelQuestStage(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      image: this.editForm.get(['image']).value,
      modelName: this.editForm.get(['modelName']).value,
      bgmOffencing: this.editForm.get(['bgmOffencing']).value,
      bgmDefencing: this.editForm.get(['bgmDefencing']).value,
      bgmHurrying: this.editForm.get(['bgmHurrying']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMModelQuestStage>>) {
    result.subscribe((res: HttpResponse<IMModelQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
