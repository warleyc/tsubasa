import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMarathonQuestWorld, MMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';
import { MMarathonQuestWorldService } from './m-marathon-quest-world.service';

@Component({
  selector: 'jhi-m-marathon-quest-world-update',
  templateUrl: './m-marathon-quest-world-update.component.html'
})
export class MMarathonQuestWorldUpdateComponent implements OnInit {
  mMarathonQuestWorld: IMMarathonQuestWorld;
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
    protected mMarathonQuestWorldService: MMarathonQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonQuestWorld }) => {
      this.updateForm(mMarathonQuestWorld);
      this.mMarathonQuestWorld = mMarathonQuestWorld;
    });
  }

  updateForm(mMarathonQuestWorld: IMMarathonQuestWorld) {
    this.editForm.patchValue({
      id: mMarathonQuestWorld.id,
      setId: mMarathonQuestWorld.setId,
      number: mMarathonQuestWorld.number,
      name: mMarathonQuestWorld.name,
      imagePath: mMarathonQuestWorld.imagePath,
      description: mMarathonQuestWorld.description,
      stageUnlockPattern: mMarathonQuestWorld.stageUnlockPattern,
      arousalBanner: mMarathonQuestWorld.arousalBanner,
      specialRewardContentType: mMarathonQuestWorld.specialRewardContentType,
      specialRewardContentId: mMarathonQuestWorld.specialRewardContentId,
      isEnableCoop: mMarathonQuestWorld.isEnableCoop
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
    const mMarathonQuestWorld = this.createFromForm();
    if (mMarathonQuestWorld.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonQuestWorldService.update(mMarathonQuestWorld));
    } else {
      this.subscribeToSaveResponse(this.mMarathonQuestWorldService.create(mMarathonQuestWorld));
    }
  }

  private createFromForm(): IMMarathonQuestWorld {
    const entity = {
      ...new MMarathonQuestWorld(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonQuestWorld>>) {
    result.subscribe((res: HttpResponse<IMMarathonQuestWorld>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
