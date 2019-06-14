import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTimeattackQuestStage, MTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';
import { MTimeattackQuestStageService } from './m-timeattack-quest-stage.service';
import { IMTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';
import { MTimeattackQuestWorldService } from 'app/entities/m-timeattack-quest-world';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-update',
  templateUrl: './m-timeattack-quest-stage-update.component.html'
})
export class MTimeattackQuestStageUpdateComponent implements OnInit {
  mTimeattackQuestStage: IMTimeattackQuestStage;
  isSaving: boolean;

  mtimeattackquestworlds: IMTimeattackQuestWorld[];

  editForm = this.fb.group({
    id: [],
    worldId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    startAt: [null, [Validators.required]],
    name: [null, [Validators.required]],
    kickoffDescription: [null, [Validators.required]],
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
    ticketId: [],
    ticketAmount: [null, [Validators.required]],
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
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTimeattackQuestStageService: MTimeattackQuestStageService,
    protected mTimeattackQuestWorldService: MTimeattackQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStage }) => {
      this.updateForm(mTimeattackQuestStage);
      this.mTimeattackQuestStage = mTimeattackQuestStage;
    });
    this.mTimeattackQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMTimeattackQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMTimeattackQuestWorld[]>) => response.body)
      )
      .subscribe(
        (res: IMTimeattackQuestWorld[]) => (this.mtimeattackquestworlds = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mTimeattackQuestStage: IMTimeattackQuestStage) {
    this.editForm.patchValue({
      id: mTimeattackQuestStage.id,
      worldId: mTimeattackQuestStage.worldId,
      number: mTimeattackQuestStage.number,
      startAt: mTimeattackQuestStage.startAt,
      name: mTimeattackQuestStage.name,
      kickoffDescription: mTimeattackQuestStage.kickoffDescription,
      imagePath: mTimeattackQuestStage.imagePath,
      characterThumbnailPath: mTimeattackQuestStage.characterThumbnailPath,
      difficulty: mTimeattackQuestStage.difficulty,
      stageUnlockPattern: mTimeattackQuestStage.stageUnlockPattern,
      storyOnly: mTimeattackQuestStage.storyOnly,
      firstHalfNpcDeckId: mTimeattackQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mTimeattackQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mTimeattackQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mTimeattackQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mTimeattackQuestStage.extraFirstHalfNpcDeckId,
      consumeAp: mTimeattackQuestStage.consumeAp,
      ticketId: mTimeattackQuestStage.ticketId,
      ticketAmount: mTimeattackQuestStage.ticketAmount,
      kickOffType: mTimeattackQuestStage.kickOffType,
      matchMinute: mTimeattackQuestStage.matchMinute,
      enableExtra: mTimeattackQuestStage.enableExtra,
      enablePk: mTimeattackQuestStage.enablePk,
      aiLevel: mTimeattackQuestStage.aiLevel,
      startAtSecondHalf: mTimeattackQuestStage.startAtSecondHalf,
      startScore: mTimeattackQuestStage.startScore,
      startScoreOpponent: mTimeattackQuestStage.startScoreOpponent,
      conditionId: mTimeattackQuestStage.conditionId,
      optionId: mTimeattackQuestStage.optionId,
      deckConditionId: mTimeattackQuestStage.deckConditionId,
      shortName: mTimeattackQuestStage.shortName,
      idId: mTimeattackQuestStage.idId
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
    const mTimeattackQuestStage = this.createFromForm();
    if (mTimeattackQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mTimeattackQuestStageService.update(mTimeattackQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mTimeattackQuestStageService.create(mTimeattackQuestStage));
    }
  }

  private createFromForm(): IMTimeattackQuestStage {
    const entity = {
      ...new MTimeattackQuestStage(),
      id: this.editForm.get(['id']).value,
      worldId: this.editForm.get(['worldId']).value,
      number: this.editForm.get(['number']).value,
      startAt: this.editForm.get(['startAt']).value,
      name: this.editForm.get(['name']).value,
      kickoffDescription: this.editForm.get(['kickoffDescription']).value,
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
      ticketId: this.editForm.get(['ticketId']).value,
      ticketAmount: this.editForm.get(['ticketAmount']).value,
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
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTimeattackQuestStage>>) {
    result.subscribe((res: HttpResponse<IMTimeattackQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMTimeattackQuestWorldById(index: number, item: IMTimeattackQuestWorld) {
    return item.id;
  }
}
