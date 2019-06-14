import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuerillaQuestWorld, MGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';
import { MGuerillaQuestWorldService } from './m-guerilla-quest-world.service';

@Component({
  selector: 'jhi-m-guerilla-quest-world-update',
  templateUrl: './m-guerilla-quest-world-update.component.html'
})
export class MGuerillaQuestWorldUpdateComponent implements OnInit {
  mGuerillaQuestWorld: IMGuerillaQuestWorld;
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
    protected mGuerillaQuestWorldService: MGuerillaQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuerillaQuestWorld }) => {
      this.updateForm(mGuerillaQuestWorld);
      this.mGuerillaQuestWorld = mGuerillaQuestWorld;
    });
  }

  updateForm(mGuerillaQuestWorld: IMGuerillaQuestWorld) {
    this.editForm.patchValue({
      id: mGuerillaQuestWorld.id,
      setId: mGuerillaQuestWorld.setId,
      number: mGuerillaQuestWorld.number,
      name: mGuerillaQuestWorld.name,
      imagePath: mGuerillaQuestWorld.imagePath,
      description: mGuerillaQuestWorld.description,
      stageUnlockPattern: mGuerillaQuestWorld.stageUnlockPattern,
      arousalBanner: mGuerillaQuestWorld.arousalBanner,
      specialRewardContentType: mGuerillaQuestWorld.specialRewardContentType,
      specialRewardContentId: mGuerillaQuestWorld.specialRewardContentId,
      isEnableCoop: mGuerillaQuestWorld.isEnableCoop
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
    const mGuerillaQuestWorld = this.createFromForm();
    if (mGuerillaQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuerillaQuestWorldService.update(mGuerillaQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mGuerillaQuestWorldService.create(mGuerillaQuestWorld));
    }
  }

  private createFromForm(): IMGuerillaQuestWorld {
    const entity = {
      ...new MGuerillaQuestWorld(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuerillaQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMGuerillaQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
