import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMWeeklyQuestStage, MWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';
import { MWeeklyQuestStageService } from './m-weekly-quest-stage.service';
import { IMWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';
import { MWeeklyQuestWorldService } from 'app/entities/m-weekly-quest-world';

@Component({
  selector: 'jhi-m-weekly-quest-stage-update',
  templateUrl: './m-weekly-quest-stage-update.component.html'
})
export class MWeeklyQuestStageUpdateComponent implements OnInit {
  mWeeklyQuestStage: IMWeeklyQuestStage;
  isSaving: boolean;

  mweeklyquestworlds: IMWeeklyQuestWorld[];

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
    mweeklyquestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mWeeklyQuestStageService: MWeeklyQuestStageService,
    protected mWeeklyQuestWorldService: MWeeklyQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mWeeklyQuestStage }) => {
      this.updateForm(mWeeklyQuestStage);
      this.mWeeklyQuestStage = mWeeklyQuestStage;
    });
    this.mWeeklyQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMWeeklyQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMWeeklyQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMWeeklyQuestWorld[]) => (this.mweeklyquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mWeeklyQuestStage: IMWeeklyQuestStage) {
    this.editForm.patchValue({
      id: mWeeklyQuestStage.id,
      worldId: mWeeklyQuestStage.worldId,
      number: mWeeklyQuestStage.number,
      name: mWeeklyQuestStage.name,
      imagePath: mWeeklyQuestStage.imagePath,
      characterThumbnailPath: mWeeklyQuestStage.characterThumbnailPath,
      difficulty: mWeeklyQuestStage.difficulty,
      stageUnlockPattern: mWeeklyQuestStage.stageUnlockPattern,
      storyOnly: mWeeklyQuestStage.storyOnly,
      firstHalfNpcDeckId: mWeeklyQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mWeeklyQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mWeeklyQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mWeeklyQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mWeeklyQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mWeeklyQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mWeeklyQuestStage.consumeAp,
      kickOffType: mWeeklyQuestStage.kickOffType,
      matchMinute: mWeeklyQuestStage.matchMinute,
      enableExtra: mWeeklyQuestStage.enableExtra,
      enablePk: mWeeklyQuestStage.enablePk,
      aiLevel: mWeeklyQuestStage.aiLevel,
      startAtSecondHalf: mWeeklyQuestStage.startAtSecondHalf,
      startScore: mWeeklyQuestStage.startScore,
      startScoreOpponent: mWeeklyQuestStage.startScoreOpponent,
      conditionId: mWeeklyQuestStage.conditionId,
      optionId: mWeeklyQuestStage.optionId,
      deckConditionId: mWeeklyQuestStage.deckConditionId,
      mweeklyquestworldId: mWeeklyQuestStage.mweeklyquestworldId
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
    const mWeeklyQuestStage = this.createFromForm();
    if (mWeeklyQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mWeeklyQuestStageService.update(mWeeklyQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mWeeklyQuestStageService.create(mWeeklyQuestStage));
    }
  }

  private createFromForm(): IMWeeklyQuestStage {
    const entity = {
      ...new MWeeklyQuestStage(),
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
      mweeklyquestworldId: this.editForm.get(['mweeklyquestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMWeeklyQuestStage>>) {
    result.subscribe((res: HttpResponse<IMWeeklyQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMWeeklyQuestWorldById(index: number, item: IMWeeklyQuestWorld) {
    return item.id;
  }
}
