import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMMarathonQuestStage, MMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';
import { MMarathonQuestStageService } from './m-marathon-quest-stage.service';
import { IMMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';
import { MMarathonQuestWorldService } from 'app/entities/m-marathon-quest-world';

@Component({
  selector: 'jhi-m-marathon-quest-stage-update',
  templateUrl: './m-marathon-quest-stage-update.component.html'
})
export class MMarathonQuestStageUpdateComponent implements OnInit {
  mMarathonQuestStage: IMMarathonQuestStage;
  isSaving: boolean;

  mmarathonquestworlds: IMMarathonQuestWorld[];

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
    mmarathonquestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mMarathonQuestStageService: MMarathonQuestStageService,
    protected mMarathonQuestWorldService: MMarathonQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mMarathonQuestStage }) => {
      this.updateForm(mMarathonQuestStage);
      this.mMarathonQuestStage = mMarathonQuestStage;
    });
    this.mMarathonQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMMarathonQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMMarathonQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMMarathonQuestWorld[]) => (this.mmarathonquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mMarathonQuestStage: IMMarathonQuestStage) {
    this.editForm.patchValue({
      id: mMarathonQuestStage.id,
      worldId: mMarathonQuestStage.worldId,
      number: mMarathonQuestStage.number,
      name: mMarathonQuestStage.name,
      imagePath: mMarathonQuestStage.imagePath,
      characterThumbnailPath: mMarathonQuestStage.characterThumbnailPath,
      difficulty: mMarathonQuestStage.difficulty,
      stageUnlockPattern: mMarathonQuestStage.stageUnlockPattern,
      storyOnly: mMarathonQuestStage.storyOnly,
      firstHalfNpcDeckId: mMarathonQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mMarathonQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mMarathonQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mMarathonQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mMarathonQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mMarathonQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mMarathonQuestStage.consumeAp,
      kickOffType: mMarathonQuestStage.kickOffType,
      matchMinute: mMarathonQuestStage.matchMinute,
      enableExtra: mMarathonQuestStage.enableExtra,
      enablePk: mMarathonQuestStage.enablePk,
      aiLevel: mMarathonQuestStage.aiLevel,
      startAtSecondHalf: mMarathonQuestStage.startAtSecondHalf,
      startScore: mMarathonQuestStage.startScore,
      startScoreOpponent: mMarathonQuestStage.startScoreOpponent,
      conditionId: mMarathonQuestStage.conditionId,
      optionId: mMarathonQuestStage.optionId,
      deckConditionId: mMarathonQuestStage.deckConditionId,
      mmarathonquestworldId: mMarathonQuestStage.mmarathonquestworldId
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
    const mMarathonQuestStage = this.createFromForm();
    if (mMarathonQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mMarathonQuestStageService.update(mMarathonQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mMarathonQuestStageService.create(mMarathonQuestStage));
    }
  }

  private createFromForm(): IMMarathonQuestStage {
    const entity = {
      ...new MMarathonQuestStage(),
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
      mmarathonquestworldId: this.editForm.get(['mmarathonquestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMarathonQuestStage>>) {
    result.subscribe((res: HttpResponse<IMMarathonQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMMarathonQuestWorldById(index: number, item: IMMarathonQuestWorld) {
    return item.id;
  }
}
