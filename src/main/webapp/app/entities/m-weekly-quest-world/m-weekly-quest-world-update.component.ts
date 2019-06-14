import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMWeeklyQuestWorld, MWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';
import { MWeeklyQuestWorldService } from './m-weekly-quest-world.service';

@Component({
  selector: 'jhi-m-weekly-quest-world-update',
  templateUrl: './m-weekly-quest-world-update.component.html'
})
export class MWeeklyQuestWorldUpdateComponent implements OnInit {
  mWeeklyQuestWorld: IMWeeklyQuestWorld;
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
    isEnableCoop: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mWeeklyQuestWorldService: MWeeklyQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mWeeklyQuestWorld }) => {
      this.updateForm(mWeeklyQuestWorld);
      this.mWeeklyQuestWorld = mWeeklyQuestWorld;
    });
  }

  updateForm(mWeeklyQuestWorld: IMWeeklyQuestWorld) {
    this.editForm.patchValue({
      id: mWeeklyQuestWorld.id,
      setId: mWeeklyQuestWorld.setId,
      number: mWeeklyQuestWorld.number,
      name: mWeeklyQuestWorld.name,
      imagePath: mWeeklyQuestWorld.imagePath,
      description: mWeeklyQuestWorld.description,
      stageUnlockPattern: mWeeklyQuestWorld.stageUnlockPattern,
      arousalBanner: mWeeklyQuestWorld.arousalBanner,
      specialRewardContentType: mWeeklyQuestWorld.specialRewardContentType,
      specialRewardContentId: mWeeklyQuestWorld.specialRewardContentId,
      isEnableCoop: mWeeklyQuestWorld.isEnableCoop
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
    const mWeeklyQuestWorld = this.createFromForm();
    if (mWeeklyQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mWeeklyQuestWorldService.update(mWeeklyQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mWeeklyQuestWorldService.create(mWeeklyQuestWorld));
    }
  }

  private createFromForm(): IMWeeklyQuestWorld {
    const entity = {
      ...new MWeeklyQuestWorld(),
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
      isEnableCoop: this.editForm.get(['isEnableCoop']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMWeeklyQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMWeeklyQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
