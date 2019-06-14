import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTimeattackQuestWorld, MTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';
import { MTimeattackQuestWorldService } from './m-timeattack-quest-world.service';

@Component({
  selector: 'jhi-m-timeattack-quest-world-update',
  templateUrl: './m-timeattack-quest-world-update.component.html'
})
export class MTimeattackQuestWorldUpdateComponent implements OnInit {
  mTimeattackQuestWorld: IMTimeattackQuestWorld;
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
    protected mTimeattackQuestWorldService: MTimeattackQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTimeattackQuestWorld }) => {
      this.updateForm(mTimeattackQuestWorld);
      this.mTimeattackQuestWorld = mTimeattackQuestWorld;
    });
  }

  updateForm(mTimeattackQuestWorld: IMTimeattackQuestWorld) {
    this.editForm.patchValue({
      id: mTimeattackQuestWorld.id,
      setId: mTimeattackQuestWorld.setId,
      number: mTimeattackQuestWorld.number,
      name: mTimeattackQuestWorld.name,
      imagePath: mTimeattackQuestWorld.imagePath,
      backgroundImagePath: mTimeattackQuestWorld.backgroundImagePath,
      description: mTimeattackQuestWorld.description,
      stageUnlockPattern: mTimeattackQuestWorld.stageUnlockPattern,
      arousalBanner: mTimeattackQuestWorld.arousalBanner,
      specialRewardContentType: mTimeattackQuestWorld.specialRewardContentType,
      specialRewardContentId: mTimeattackQuestWorld.specialRewardContentId,
      isEnableCoop: mTimeattackQuestWorld.isEnableCoop
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
    const mTimeattackQuestWorld = this.createFromForm();
    if (mTimeattackQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mTimeattackQuestWorldService.update(mTimeattackQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mTimeattackQuestWorldService.create(mTimeattackQuestWorld));
    }
  }

  private createFromForm(): IMTimeattackQuestWorld {
    const entity = {
      ...new MTimeattackQuestWorld(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTimeattackQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMTimeattackQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
