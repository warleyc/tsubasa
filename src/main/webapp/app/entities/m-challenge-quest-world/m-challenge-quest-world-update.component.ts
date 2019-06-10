import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMChallengeQuestWorld, MChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';
import { MChallengeQuestWorldService } from './m-challenge-quest-world.service';

@Component({
  selector: 'jhi-m-challenge-quest-world-update',
  templateUrl: './m-challenge-quest-world-update.component.html'
})
export class MChallengeQuestWorldUpdateComponent implements OnInit {
  mChallengeQuestWorld: IMChallengeQuestWorld;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    setId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    imagePath: [null, [Validators.required]],
    backgroundImagePath: [null, [Validators.required]],
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
    protected mChallengeQuestWorldService: MChallengeQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChallengeQuestWorld }) => {
      this.updateForm(mChallengeQuestWorld);
      this.mChallengeQuestWorld = mChallengeQuestWorld;
    });
  }

  updateForm(mChallengeQuestWorld: IMChallengeQuestWorld) {
    this.editForm.patchValue({
      id: mChallengeQuestWorld.id,
      setId: mChallengeQuestWorld.setId,
      number: mChallengeQuestWorld.number,
      name: mChallengeQuestWorld.name,
      imagePath: mChallengeQuestWorld.imagePath,
      backgroundImagePath: mChallengeQuestWorld.backgroundImagePath,
      description: mChallengeQuestWorld.description,
      stageUnlockPattern: mChallengeQuestWorld.stageUnlockPattern,
      arousalBanner: mChallengeQuestWorld.arousalBanner,
      specialRewardContentType: mChallengeQuestWorld.specialRewardContentType,
      specialRewardContentId: mChallengeQuestWorld.specialRewardContentId,
      isEnableCoop: mChallengeQuestWorld.isEnableCoop
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
    const mChallengeQuestWorld = this.createFromForm();
    if (mChallengeQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mChallengeQuestWorldService.update(mChallengeQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mChallengeQuestWorldService.create(mChallengeQuestWorld));
    }
  }

  private createFromForm(): IMChallengeQuestWorld {
    const entity = {
      ...new MChallengeQuestWorld(),
      id: this.editForm.get(['id']).value,
      setId: this.editForm.get(['setId']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      backgroundImagePath: this.editForm.get(['backgroundImagePath']).value,
      description: this.editForm.get(['description']).value,
      stageUnlockPattern: this.editForm.get(['stageUnlockPattern']).value,
      arousalBanner: this.editForm.get(['arousalBanner']).value,
      specialRewardContentType: this.editForm.get(['specialRewardContentType']).value,
      specialRewardContentId: this.editForm.get(['specialRewardContentId']).value,
      isEnableCoop: this.editForm.get(['isEnableCoop']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChallengeQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMChallengeQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
