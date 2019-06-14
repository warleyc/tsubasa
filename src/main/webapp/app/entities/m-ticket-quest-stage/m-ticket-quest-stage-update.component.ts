import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMTicketQuestStage, MTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';
import { MTicketQuestStageService } from './m-ticket-quest-stage.service';
import { IMTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';
import { MTicketQuestWorldService } from 'app/entities/m-ticket-quest-world';

@Component({
  selector: 'jhi-m-ticket-quest-stage-update',
  templateUrl: './m-ticket-quest-stage-update.component.html'
})
export class MTicketQuestStageUpdateComponent implements OnInit {
  mTicketQuestStage: IMTicketQuestStage;
  isSaving: boolean;

  mticketquestworlds: IMTicketQuestWorld[];

  editForm = this.fb.group({
    id: [],
    worldId: [null, [Validators.required]],
    number: [null, [Validators.required]],
    name: [null, [Validators.required]],
    ticketId: [null, [Validators.required]],
    ticketAmount: [null, [Validators.required]],
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
    mticketquestworldId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mTicketQuestStageService: MTicketQuestStageService,
    protected mTicketQuestWorldService: MTicketQuestWorldService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mTicketQuestStage }) => {
      this.updateForm(mTicketQuestStage);
      this.mTicketQuestStage = mTicketQuestStage;
    });
    this.mTicketQuestWorldService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMTicketQuestWorld[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMTicketQuestWorld[]>) => response.body)
      )
      .subscribe((res: IMTicketQuestWorld[]) => (this.mticketquestworlds = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mTicketQuestStage: IMTicketQuestStage) {
    this.editForm.patchValue({
      id: mTicketQuestStage.id,
      worldId: mTicketQuestStage.worldId,
      number: mTicketQuestStage.number,
      name: mTicketQuestStage.name,
      ticketId: mTicketQuestStage.ticketId,
      ticketAmount: mTicketQuestStage.ticketAmount,
      imagePath: mTicketQuestStage.imagePath,
      characterThumbnailPath: mTicketQuestStage.characterThumbnailPath,
      difficulty: mTicketQuestStage.difficulty,
      stageUnlockPattern: mTicketQuestStage.stageUnlockPattern,
      storyOnly: mTicketQuestStage.storyOnly,
      firstHalfNpcDeckId: mTicketQuestStage.firstHalfNpcDeckId,
      firstHalfEnvironmentId: mTicketQuestStage.firstHalfEnvironmentId,
      secondHalfNpcDeckId: mTicketQuestStage.secondHalfNpcDeckId,
      secondHalfEnvironmentId: mTicketQuestStage.secondHalfEnvironmentId,
      extraFirstHalfNpcDeckId: mTicketQuestStage.extraFirstHalfNpcDeckId,
      extraSecondHalfNpcDeckId: mTicketQuestStage.extraSecondHalfNpcDeckId,
      consumeAp: mTicketQuestStage.consumeAp,
      kickOffType: mTicketQuestStage.kickOffType,
      matchMinute: mTicketQuestStage.matchMinute,
      enableExtra: mTicketQuestStage.enableExtra,
      enablePk: mTicketQuestStage.enablePk,
      aiLevel: mTicketQuestStage.aiLevel,
      startAtSecondHalf: mTicketQuestStage.startAtSecondHalf,
      startScore: mTicketQuestStage.startScore,
      startScoreOpponent: mTicketQuestStage.startScoreOpponent,
      conditionId: mTicketQuestStage.conditionId,
      optionId: mTicketQuestStage.optionId,
      deckConditionId: mTicketQuestStage.deckConditionId,
      mticketquestworldId: mTicketQuestStage.mticketquestworldId
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
    const mTicketQuestStage = this.createFromForm();
    if (mTicketQuestStage.id !== undefined) {
      this.subscribeToSaveResponse(this.mTicketQuestStageService.update(mTicketQuestStage));
    } else {
      this.subscribeToSaveResponse(this.mTicketQuestStageService.create(mTicketQuestStage));
    }
  }

  private createFromForm(): IMTicketQuestStage {
    const entity = {
      ...new MTicketQuestStage(),
      id: this.editForm.get(['id']).value,
      worldId: this.editForm.get(['worldId']).value,
      number: this.editForm.get(['number']).value,
      name: this.editForm.get(['name']).value,
      ticketId: this.editForm.get(['ticketId']).value,
      ticketAmount: this.editForm.get(['ticketAmount']).value,
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
      mticketquestworldId: this.editForm.get(['mticketquestworldId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMTicketQuestStage>>) {
    result.subscribe((res: HttpResponse<IMTicketQuestStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMTicketQuestWorldById(index: number, item: IMTicketQuestWorld) {
    return item.id;
  }
}
