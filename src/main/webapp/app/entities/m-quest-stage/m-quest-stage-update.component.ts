import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMQuestStage, MQuestStage } from 'app/shared/model/m-quest-stage.model';
import { MQuestStageService } from './m-quest-stage.service';
import { IMQuestWorld } from 'app/shared/model/m-quest-world.model';
import { MQuestWorldService } from 'app/entities/m-quest-world';

@Component({
  selector: 'jhi-m-quest-stage-update',
  templateUrl: './m-quest-stage-update.component.html'
})
export class MQuestStageUpdateComponent implements OnInit {
  mQuestStage: IMQuestStage;
  isSaving: boolean;

  mquestworlds: IMQuestWorld[];

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
    mquestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mQuestStageService: MQuestStageService,
    protected mQuestWorldService: MQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestStage }) => {
      this.updateForm(mQuestStage);
      this.mQuestStage = mQuestStage;
    });
    this.mQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMQuestWorld[]) => (this.mquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mQuestStage: IMQuestStage) {
    this.editForm.patchValue({
      id: mQuestStage.id,
      worldId: mQuestStage.worldId,
      number: mQuestStage.number,
      name: mQuestStage.name,
      imagePath: mQuestStage.imagePath,
      characterThumbnailPath: mQuestStage.characterThumbnailPath,
      difficulty: mQuestStage.difficulty,
      stageUnlockPattern: mQuestStage.stageUnlockPattern,
      storyOnly: mQuestStage.storyOnly,
      firstHalfNpcDeckId: mQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mQuestStage.extraFirstHalfNpcDeckId,
      consumeAp: mQuestStage.consumeAp,
      kickOffType: mQuestStage.kickOffType,
      matchMinute: mQuestStage.matchMinute,
      enableExtra: mQuestStage.enableExtra,
      enablePk: mQuestStage.enablePk,
      aiLevel: mQuestStage.aiLevel,
      startAtSecondHalf: mQuestStage.startAtSecondHalf,
      startScore: mQuestStage.startScore,
      startScoreOpponent: mQuestStage.startScoreOpponent,
      conditionId: mQuestStage.conditionId,
      optionId: mQuestStage.optionId,
      deckConditionId: mQuestStage.deckConditionId,
      mquestworldId: mQuestStage.mquestworldId
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
    const mQuestStage = this.createFromForm();
    if (mQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestStageService.update(mQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mQuestStageService.create(mQuestStage));
    }
  }

  private createFromForm(): IMQuestStage {
    const entity = {
      ...new MQuestStage(),
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
      mquestworldId: this.editForm.get(['mquestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestStage>>) {
    result.subscribe((res: HttpResponse<IMQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMQuestWorldById(index: number, item: IMQuestWorld) {
    return item.id;
  }
}
