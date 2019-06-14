import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLuckWeeklyQuestWorld, MLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';
import { MLuckWeeklyQuestWorldService } from './m-luck-weekly-quest-world.service';

@Component({
  selector: 'jhi-m-luck-weekly-quest-world-update',
  templateUrl: './m-luck-weekly-quest-world-update.component.html'
})
export class MLuckWeeklyQuestWorldUpdateComponent implements OnInit {
  mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    setId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    imagePath: [null, [Validators.required]],
    description: [null, [Validators.required]],
    stageUnlockPattern: [null, [Validators.required]],
    arousalBanner: [],
    specialRewardContentType: [],
    specialRewardContentId: [],
    isEnableCoop: [null, [Validators.required]],
    clearLimit: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLuckWeeklyQuestWorldService: MLuckWeeklyQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestWorld }) => {
      this.updateForm(mLuckWeeklyQuestWorld);
      this.mLuckWeeklyQuestWorld = mLuckWeeklyQuestWorld;
    });
  }

  updateForm(mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld) {
    this.editForm.patchValue({
      id: mLuckWeeklyQuestWorld.id,
      setId: mLuckWeeklyQuestWorld.setId,
      number: mLuckWeeklyQuestWorld.number,
      name: mLuckWeeklyQuestWorld.name,
      imagePath: mLuckWeeklyQuestWorld.imagePath,
      description: mLuckWeeklyQuestWorld.description,
      stageUnlockPattern: mLuckWeeklyQuestWorld.stageUnlockPattern,
      arousalBanner: mLuckWeeklyQuestWorld.arousalBanner,
      specialRewardContentType: mLuckWeeklyQuestWorld.specialRewardContentType,
      specialRewardContentId: mLuckWeeklyQuestWorld.specialRewardContentId,
      isEnableCoop: mLuckWeeklyQuestWorld.isEnableCoop,
      clearLimit: mLuckWeeklyQuestWorld.clearLimit
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
    const mLuckWeeklyQuestWorld = this.createFromForm();
    if (mLuckWeeklyQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestWorldService.update(mLuckWeeklyQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestWorldService.create(mLuckWeeklyQuestWorld));
    }
  }

  private createFromForm(): IMLuckWeeklyQuestWorld {
    const entity = {
      ...new MLuckWeeklyQuestWorld(),
      id: this.editForm.get(['id']).value,
      setId: this.editForm.get(['setId']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      description: this.editForm.get(['description']).value,
      stageUnlockPattern: this.editForm.get(['stageUnlockPattern']).value,
      arousalBanner: this.editForm.get(['arousalBanner']).value,
      specialRewardContentType: this.editForm.get(['specialRewardContentType']).value,
      specialRewardContentId: this.editForm.get(['specialRewardContentId']).value,
      isEnableCoop: this.editForm.get(['isEnableCoop']).value,
      clearLimit: this.editForm.get(['clearLimit']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLuckWeeklyQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMLuckWeeklyQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
