import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMQuestWorld, MQuestWorld } from 'app/shared/model/m-quest-world.model';
import { MQuestWorldService } from './m-quest-world.service';

@Component({
  selector: 'jhi-m-quest-world-update',
  templateUrl: './m-quest-world-update.component.html'
})
export class MQuestWorldUpdateComponent implements OnInit {
  mQuestWorld: IMQuestWorld;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    imagePath: [null, [Validators.required]],
    backgroundImagePath: [null, [Validators.required]],
    description: [null, [Validators.required]],
    stageUnlockPattern: [null, [Validators.required]],
    specialRewardContentType: [],
    specialRewardContentId: [],
    isEnableCoop: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mQuestWorldService: MQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestWorld }) => {
      this.updateForm(mQuestWorld);
      this.mQuestWorld = mQuestWorld;
    });
  }

  updateForm(mQuestWorld: IMQuestWorld) {
    this.editForm.patchValue({
      id: mQuestWorld.id,
      number: mQuestWorld.number,
      name: mQuestWorld.name,
      startAt: mQuestWorld.startAt,
      imagePath: mQuestWorld.imagePath,
      backgroundImagePath: mQuestWorld.backgroundImagePath,
      description: mQuestWorld.description,
      stageUnlockPattern: mQuestWorld.stageUnlockPattern,
      specialRewardContentType: mQuestWorld.specialRewardContentType,
      specialRewardContentId: mQuestWorld.specialRewardContentId,
      isEnableCoop: mQuestWorld.isEnableCoop
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
    const mQuestWorld = this.createFromForm();
    if (mQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestWorldService.update(mQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mQuestWorldService.create(mQuestWorld));
    }
  }

  private createFromForm(): IMQuestWorld {
    const entity = {
      ...new MQuestWorld(),
      id: this.editForm.get(['id']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      startAt: this.editForm.get(['startAt']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      backgroundImagePath: this.editForm.get(['backgroundImagePath']).value,
      description: this.editForm.get(['description']).value,
      stageUnlockPattern: this.editForm.get(['stageUnlockPattern']).value,
      specialRewardContentType: this.editForm.get(['specialRewardContentType']).value,
      specialRewardContentId: this.editForm.get(['specialRewardContentId']).value,
      isEnableCoop: this.editForm.get(['isEnableCoop']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
