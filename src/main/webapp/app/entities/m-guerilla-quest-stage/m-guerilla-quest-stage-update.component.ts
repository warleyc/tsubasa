import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMGuerillaQuestStage, MGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';
import { MGuerillaQuestStageService } from './m-guerilla-quest-stage.service';
import { IMGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';
import { MGuerillaQuestWorldService } from 'app/entities/m-guerilla-quest-world';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-update',
  templateUrl: './m-guerilla-quest-stage-update.component.html'
})
export class MGuerillaQuestStageUpdateComponent implements OnInit {
  mGuerillaQuestStage: IMGuerillaQuestStage;
  isSaving: boolean;

  mguerillaquestworlds: IMGuerillaQuestWorld[];

  editForm = this.fb.group({
    id: [],
    worldId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    imagePath: [null, [Validators.required]],
    characterThumbnailPath: [null, [Validators.required]],
    difficulty: [null, [Validators.required]],
    stageUnlockPattern: [null, [Validators.required]],
    storyOnly: [null, [Validators.required]],
    firstHalfNpcDeckId: [null, [Validators.required]],
    firstHalfEnvironmentId: [null, [Validators.required]],
    secondHalfNpcDeckId: [null, [Validators.required]],
    secondHalfEnvironmentId: [null, [Validators.required]],
    extraFirstHalfNpcDeckId: [null, [Validators.required]],
    extraSecondHalfNpcDeckId: [null, [Validators.required]],
    consumeAp: [null, [Validators.required]],
    kickOffType: [null, [Validators.required]],
    matchMinute: [null, [Validators.required]],
    enableExtra: [null, [Validators.required]],
    enablePk: [null, [Validators.required]],
    aiLevel: [null, [Validators.required]],
    startAtSecondHalf: [null, [Validators.required]],
    startScore: [null, [Validators.required]],
    startScoreOpponent: [null, [Validators.required]],
    conditionId: [],
    optionId: [],
    deckConditionId: [],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mGuerillaQuestStageService: MGuerillaQuestStageService,
    protected mGuerillaQuestWorldService: MGuerillaQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStage }) => {
      this.updateForm(mGuerillaQuestStage);
      this.mGuerillaQuestStage = mGuerillaQuestStage;
    });
    this.mGuerillaQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMGuerillaQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMGuerillaQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMGuerillaQuestWorld[]) => (this.mguerillaquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mGuerillaQuestStage: IMGuerillaQuestStage) {
    this.editForm.patchValue({
      id: mGuerillaQuestStage.id,
      worldId: mGuerillaQuestStage.worldId,
      number: mGuerillaQuestStage.number,
      name: mGuerillaQuestStage.name,
      imagePath: mGuerillaQuestStage.imagePath,
      characterThumbnailPath: mGuerillaQuestStage.characterThumbnailPath,
      difficulty: mGuerillaQuestStage.difficulty,
      stageUnlockPattern: mGuerillaQuestStage.stageUnlockPattern,
      storyOnly: mGuerillaQuestStage.storyOnly,
      firstHalfNpcDeckId: mGuerillaQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mGuerillaQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mGuerillaQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mGuerillaQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mGuerillaQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mGuerillaQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mGuerillaQuestStage.consumeAp,
      kickOffType: mGuerillaQuestStage.kickOffType,
      matchMinute: mGuerillaQuestStage.matchMinute,
      enableExtra: mGuerillaQuestStage.enableExtra,
      enablePk: mGuerillaQuestStage.enablePk,
      aiLevel: mGuerillaQuestStage.aiLevel,
      startAtSecondHalf: mGuerillaQuestStage.startAtSecondHalf,
      startScore: mGuerillaQuestStage.startScore,
      startScoreOpponent: mGuerillaQuestStage.startScoreOpponent,
      conditionId: mGuerillaQuestStage.conditionId,
      optionId: mGuerillaQuestStage.optionId,
      deckConditionId: mGuerillaQuestStage.deckConditionId,
      idId: mGuerillaQuestStage.idId
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
    const mGuerillaQuestStage = this.createFromForm();
    if (mGuerillaQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mGuerillaQuestStageService.update(mGuerillaQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mGuerillaQuestStageService.create(mGuerillaQuestStage));
    }
  }

  private createFromForm(): IMGuerillaQuestStage {
    const entity = {
      ...new MGuerillaQuestStage(),
      id: this.editForm.get(['id']).value,
      worldId: this.editForm.get(['worldId']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      characterThumbnailPath: this.editForm.get(['characterThumbnailPath']).value,
      difficulty: this.editForm.get(['difficulty']).value,
      stageUnlockPattern: this.editForm.get(['stageUnlockPattern']).value,
      storyOnly: this.editForm.get(['storyOnly']).value,
      firstHalfNpcDeckId: this.editForm.get(['firstHalfNpcDeckId']).value,
      firstHalfEnvironmentId: this.editForm.get(['firstHalfEnvironmentId']).value,
      secondHalfNpcDeckId: this.editForm.get(['secondHalfNpcDeckId']).value,
      secondHalfEnvironmentId: this.editForm.get(['secondHalfEnvironmentId']).value,
      extraFirstHalfNpcDeckId: this.editForm.get(['extraFirstHalfNpcDeckId']).value,
      extraSecondHalfNpcDeckId: this.editForm.get(['extraSecondHalfNpcDeckId']).value,
      consumeAp: this.editForm.get(['consumeAp']).value,
      kickOffType: this.editForm.get(['kickOffType']).value,
      matchMinute: this.editForm.get(['matchMinute']).value,
      enableExtra: this.editForm.get(['enableExtra']).value,
      enablePk: this.editForm.get(['enablePk']).value,
      aiLevel: this.editForm.get(['aiLevel']).value,
      startAtSecondHalf: this.editForm.get(['startAtSecondHalf']).value,
      startScore: this.editForm.get(['startScore']).value,
      startScoreOpponent: this.editForm.get(['startScoreOpponent']).value,
      conditionId: this.editForm.get(['conditionId']).value,
      optionId: this.editForm.get(['optionId']).value,
      deckConditionId: this.editForm.get(['deckConditionId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMGuerillaQuestStage>>) {
    result.subscribe((res: HttpResponse<IMGuerillaQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMGuerillaQuestWorldById(index: number, item: IMGuerillaQuestWorld) {
    return item.id;
  }
}
