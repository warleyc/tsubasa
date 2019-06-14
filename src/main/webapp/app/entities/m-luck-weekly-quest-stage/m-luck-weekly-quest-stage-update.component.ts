import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMLuckWeeklyQuestStage, MLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';
import { MLuckWeeklyQuestStageService } from './m-luck-weekly-quest-stage.service';
import { IMLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';
import { MLuckWeeklyQuestWorldService } from 'app/entities/m-luck-weekly-quest-world';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-update',
  templateUrl: './m-luck-weekly-quest-stage-update.component.html'
})
export class MLuckWeeklyQuestStageUpdateComponent implements OnInit {
  mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage;
  isSaving: boolean;

  mluckweeklyquestworlds: IMLuckWeeklyQuestWorld[];

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
    luckId: [],
    mluckweeklyquestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mLuckWeeklyQuestStageService: MLuckWeeklyQuestStageService,
    protected mLuckWeeklyQuestWorldService: MLuckWeeklyQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStage }) => {
      this.updateForm(mLuckWeeklyQuestStage);
      this.mLuckWeeklyQuestStage = mLuckWeeklyQuestStage;
    });
    this.mLuckWeeklyQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMLuckWeeklyQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMLuckWeeklyQuestWorld[]>) => response.body)
      )
      .subscribe(
        (res: IMLuckWeeklyQuestWorld[]) => (this.mluckweeklyquestworlds = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage) {
    this.editForm.patchValue({
      id: mLuckWeeklyQuestStage.id,
      worldId: mLuckWeeklyQuestStage.worldId,
      number: mLuckWeeklyQuestStage.number,
      name: mLuckWeeklyQuestStage.name,
      imagePath: mLuckWeeklyQuestStage.imagePath,
      characterThumbnailPath: mLuckWeeklyQuestStage.characterThumbnailPath,
      difficulty: mLuckWeeklyQuestStage.difficulty,
      stageUnlockPattern: mLuckWeeklyQuestStage.stageUnlockPattern,
      storyOnly: mLuckWeeklyQuestStage.storyOnly,
      firstHalfNpcDeckId: mLuckWeeklyQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mLuckWeeklyQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mLuckWeeklyQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mLuckWeeklyQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mLuckWeeklyQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mLuckWeeklyQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mLuckWeeklyQuestStage.consumeAp,
      kickOffType: mLuckWeeklyQuestStage.kickOffType,
      matchMinute: mLuckWeeklyQuestStage.matchMinute,
      enableExtra: mLuckWeeklyQuestStage.enableExtra,
      enablePk: mLuckWeeklyQuestStage.enablePk,
      aiLevel: mLuckWeeklyQuestStage.aiLevel,
      startAtSecondHalf: mLuckWeeklyQuestStage.startAtSecondHalf,
      startScore: mLuckWeeklyQuestStage.startScore,
      startScoreOpponent: mLuckWeeklyQuestStage.startScoreOpponent,
      conditionId: mLuckWeeklyQuestStage.conditionId,
      optionId: mLuckWeeklyQuestStage.optionId,
      deckConditionId: mLuckWeeklyQuestStage.deckConditionId,
      luckId: mLuckWeeklyQuestStage.luckId,
      mluckweeklyquestworldId: mLuckWeeklyQuestStage.mluckweeklyquestworldId
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
    const mLuckWeeklyQuestStage = this.createFromForm();
    if (mLuckWeeklyQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestStageService.update(mLuckWeeklyQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mLuckWeeklyQuestStageService.create(mLuckWeeklyQuestStage));
    }
  }

  private createFromForm(): IMLuckWeeklyQuestStage {
    const entity = {
      ...new MLuckWeeklyQuestStage(),
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
      luckId: this.editForm.get(['luckId']).value,
      mluckweeklyquestworldId: this.editForm.get(['mluckweeklyquestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMLuckWeeklyQuestStage>>) {
    result.subscribe((res: HttpResponse<IMLuckWeeklyQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMLuckWeeklyQuestWorldById(index: number, item: IMLuckWeeklyQuestWorld) {
    return item.id;
  }
}
