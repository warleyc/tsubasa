import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMStageStory, MStageStory } from 'app/shared/model/m-stage-story.model';
import { MStageStoryService } from './m-stage-story.service';

@Component({
  selector: 'jhi-m-stage-story-update',
  templateUrl: './m-stage-story-update.component.html'
})
export class MStageStoryUpdateComponent implements OnInit {
  mStageStory: IMStageStory;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    stageId: [null, [Validators.required]],
    eventType: [null, [Validators.required]],
    mainStoryAsset: [],
    kickoffStoryAsset: [],
    halftimeStoryAsset: [],
    resultStoryAsset: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mStageStoryService: MStageStoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mStageStory }) => {
      this.updateForm(mStageStory);
      this.mStageStory = mStageStory;
    });
  }

  updateForm(mStageStory: IMStageStory) {
    this.editForm.patchValue({
      id: mStageStory.id,
      stageId: mStageStory.stageId,
      eventType: mStageStory.eventType,
      mainStoryAsset: mStageStory.mainStoryAsset,
      kickoffStoryAsset: mStageStory.kickoffStoryAsset,
      halftimeStoryAsset: mStageStory.halftimeStoryAsset,
      resultStoryAsset: mStageStory.resultStoryAsset
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
    const mStageStory = this.createFromForm();
    if (mStageStory.id !== undefined) {
      this.subscribeToSaveResponse(this.mStageStoryService.update(mStageStory));
    } else {
      this.subscribeToSaveResponse(this.mStageStoryService.create(mStageStory));
    }
  }

  private createFromForm(): IMStageStory {
    const entity = {
      ...new MStageStory(),
      id: this.editForm.get(['id']).value,
      stageId: this.editForm.get(['stageId']).value,
      eventType: this.editForm.get(['eventType']).value,
      mainStoryAsset: this.editForm.get(['mainStoryAsset']).value,
      kickoffStoryAsset: this.editForm.get(['kickoffStoryAsset']).value,
      halftimeStoryAsset: this.editForm.get(['halftimeStoryAsset']).value,
      resultStoryAsset: this.editForm.get(['resultStoryAsset']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMStageStory>>) {
    result.subscribe((res: HttpResponse<IMStageStory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
