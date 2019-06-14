import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMChallengeQuestStage, MChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';
import { MChallengeQuestStageService } from './m-challenge-quest-stage.service';
import { IMChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';
import { MChallengeQuestWorldService } from 'app/entities/m-challenge-quest-world';

@Component({
  selector: 'jhi-m-challenge-quest-stage-update',
  templateUrl: './m-challenge-quest-stage-update.component.html'
})
export class MChallengeQuestStageUpdateComponent implements OnInit {
  mChallengeQuestStage: IMChallengeQuestStage;
  isSaving: boolean;

  mchallengequestworlds: IMChallengeQuestWorld[];

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
    shortName: [null, [Validators.required]],
    skipCheckPoint: [null, [Validators.required]],
    roadNumber: [null, [Validators.required]],
    mchallengequestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mChallengeQuestStageService: MChallengeQuestStageService,
    protected mChallengeQuestWorldService: MChallengeQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mChallengeQuestStage }) => {
      this.updateForm(mChallengeQuestStage);
      this.mChallengeQuestStage = mChallengeQuestStage;
    });
    this.mChallengeQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMChallengeQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMChallengeQuestWorld[]>) => response.body)
      )
      .subscribe(
        (res: IMChallengeQuestWorld[]) => (this.mchallengequestworlds = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mChallengeQuestStage: IMChallengeQuestStage) {
    this.editForm.patchValue({
      id: mChallengeQuestStage.id,
      worldId: mChallengeQuestStage.worldId,
      number: mChallengeQuestStage.number,
      name: mChallengeQuestStage.name,
      imagePath: mChallengeQuestStage.imagePath,
      characterThumbnailPath: mChallengeQuestStage.characterThumbnailPath,
      difficulty: mChallengeQuestStage.difficulty,
      stageUnlockPattern: mChallengeQuestStage.stageUnlockPattern,
      storyOnly: mChallengeQuestStage.storyOnly,
      firstHalfNpcDeckId: mChallengeQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mChallengeQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mChallengeQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mChallengeQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mChallengeQuestStage.extraFirstHalfNpcDeckId,
      consumeAp: mChallengeQuestStage.consumeAp,
      kickOffType: mChallengeQuestStage.kickOffType,
      matchMinute: mChallengeQuestStage.matchMinute,
      enableExtra: mChallengeQuestStage.enableExtra,
      enablePk: mChallengeQuestStage.enablePk,
      aiLevel: mChallengeQuestStage.aiLevel,
      startAtSecondHalf: mChallengeQuestStage.startAtSecondHalf,
      startScore: mChallengeQuestStage.startScore,
      startScoreOpponent: mChallengeQuestStage.startScoreOpponent,
      conditionId: mChallengeQuestStage.conditionId,
      optionId: mChallengeQuestStage.optionId,
      deckConditionId: mChallengeQuestStage.deckConditionId,
      shortName: mChallengeQuestStage.shortName,
      skipCheckPoint: mChallengeQuestStage.skipCheckPoint,
      roadNumber: mChallengeQuestStage.roadNumber,
      mchallengequestworldId: mChallengeQuestStage.mchallengequestworldId
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
    const mChallengeQuestStage = this.createFromForm();
    if (mChallengeQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mChallengeQuestStageService.update(mChallengeQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mChallengeQuestStageService.create(mChallengeQuestStage));
    }
  }

  private createFromForm(): IMChallengeQuestStage {
    const entity = {
      ...new MChallengeQuestStage(),
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
      shortName: this.editForm.get(['shortName']).value,
      skipCheckPoint: this.editForm.get(['skipCheckPoint']).value,
      roadNumber: this.editForm.get(['roadNumber']).value,
      mchallengequestworldId: this.editForm.get(['mchallengequestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMChallengeQuestStage>>) {
    result.subscribe((res: HttpResponse<IMChallengeQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMChallengeQuestWorldById(index: number, item: IMChallengeQuestWorld) {
    return item.id;
  }
}
