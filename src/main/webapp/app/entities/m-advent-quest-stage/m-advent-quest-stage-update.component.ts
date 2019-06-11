import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMAdventQuestStage, MAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';
import { MAdventQuestStageService } from './m-advent-quest-stage.service';
import { IMAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';
import { MAdventQuestWorldService } from 'app/entities/m-advent-quest-world';

@Component({
  selector: 'jhi-m-advent-quest-stage-update',
  templateUrl: './m-advent-quest-stage-update.component.html'
})
export class MAdventQuestStageUpdateComponent implements OnInit {
  isSaving: boolean;

  madventquestworlds: IMAdventQuestWorld[];

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
    protected mAdventQuestStageService: MAdventQuestStageService,
    protected mAdventQuestWorldService: MAdventQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mAdventQuestStage }) => {
      this.updateForm(mAdventQuestStage);
    });
    this.mAdventQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMAdventQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMAdventQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMAdventQuestWorld[]) => (this.madventquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mAdventQuestStage: IMAdventQuestStage) {
    this.editForm.patchValue({
      id: mAdventQuestStage.id,
      worldId: mAdventQuestStage.worldId,
      number: mAdventQuestStage.number,
      name: mAdventQuestStage.name,
      imagePath: mAdventQuestStage.imagePath,
      characterThumbnailPath: mAdventQuestStage.characterThumbnailPath,
      difficulty: mAdventQuestStage.difficulty,
      stageUnlockPattern: mAdventQuestStage.stageUnlockPattern,
      storyOnly: mAdventQuestStage.storyOnly,
      firstHalfNpcDeckId: mAdventQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mAdventQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mAdventQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mAdventQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mAdventQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mAdventQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mAdventQuestStage.consumeAp,
      kickOffType: mAdventQuestStage.kickOffType,
      matchMinute: mAdventQuestStage.matchMinute,
      enableExtra: mAdventQuestStage.enableExtra,
      enablePk: mAdventQuestStage.enablePk,
      aiLevel: mAdventQuestStage.aiLevel,
      startAtSecondHalf: mAdventQuestStage.startAtSecondHalf,
      startScore: mAdventQuestStage.startScore,
      startScoreOpponent: mAdventQuestStage.startScoreOpponent,
      conditionId: mAdventQuestStage.conditionId,
      optionId: mAdventQuestStage.optionId,
      deckConditionId: mAdventQuestStage.deckConditionId,
      idId: mAdventQuestStage.idId
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
    const mAdventQuestStage = this.createFromForm();
    if (mAdventQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mAdventQuestStageService.update(mAdventQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mAdventQuestStageService.create(mAdventQuestStage));
    }
  }

  private createFromForm(): IMAdventQuestStage {
    const entity = {
      ...new MAdventQuestStage(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMAdventQuestStage>>) {
    result.subscribe((res: HttpResponse<IMAdventQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMAdventQuestWorldById(index: number, item: IMAdventQuestWorld) {
    return item.id;
  }
}
