import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMPlayableCard, MPlayableCard } from 'app/shared/model/m-playable-card.model';
import { MPlayableCardService } from './m-playable-card.service';
import { IMModelCard } from 'app/shared/model/m-model-card.model';
import { MModelCardService } from 'app/entities/m-model-card';

@Component({
  selector: 'jhi-m-playable-card-update',
  templateUrl: './m-playable-card-update.component.html'
})
export class MPlayableCardUpdateComponent implements OnInit {
  mPlayableCard: IMPlayableCard;
  isSaving: boolean;

  mmodelcards: IMModelCard[];

  editForm = this.fb.group({
    id: [],
    modelId: [null, [Validators.required]],
    properPositionGk: [null, [Validators.required]],
    properPositionFw: [null, [Validators.required]],
    properPositionOmf: [null, [Validators.required]],
    properPositionDmf: [null, [Validators.required]],
    properPositionDf: [null, [Validators.required]],
    characterId: [null, [Validators.required]],
    nickName: [null, [Validators.required]],
    teamId: [null, [Validators.required]],
    nationalityId: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    attribute: [null, [Validators.required]],
    thumbnailAssetsId: [null, [Validators.required]],
    cardIllustAssetsId: [null, [Validators.required]],
    playableAssetsId: [null, [Validators.required]],
    teamEffectId: [],
    triggerEffectId: [],
    maxActionSlot: [null, [Validators.required]],
    initialActionId1: [],
    initialActionId2: [],
    initialActionId3: [],
    initialActionId4: [],
    initialActionId5: [],
    initialStamina: [null, [Validators.required]],
    initialDribble: [null, [Validators.required]],
    initialShoot: [null, [Validators.required]],
    initialPass: [null, [Validators.required]],
    initialTackle: [null, [Validators.required]],
    initialBlock: [null, [Validators.required]],
    initialIntercept: [null, [Validators.required]],
    initialSpeed: [null, [Validators.required]],
    initialPower: [null, [Validators.required]],
    initialTechnique: [null, [Validators.required]],
    initialPunching: [null, [Validators.required]],
    initialCatching: [null, [Validators.required]],
    maxStamina: [null, [Validators.required]],
    maxDribble: [null, [Validators.required]],
    maxShoot: [null, [Validators.required]],
    maxPass: [null, [Validators.required]],
    maxTackle: [null, [Validators.required]],
    maxBlock: [null, [Validators.required]],
    maxIntercept: [null, [Validators.required]],
    maxSpeed: [null, [Validators.required]],
    maxPower: [null, [Validators.required]],
    maxTechnique: [null, [Validators.required]],
    maxPunching: [null, [Validators.required]],
    maxCatching: [null, [Validators.required]],
    maxPlusDribble: [null, [Validators.required]],
    maxPlusShoot: [null, [Validators.required]],
    maxPlusPass: [null, [Validators.required]],
    maxPlusTackle: [null, [Validators.required]],
    maxPlusBlock: [null, [Validators.required]],
    maxPlusIntercept: [null, [Validators.required]],
    maxPlusSpeed: [null, [Validators.required]],
    maxPlusPower: [null, [Validators.required]],
    maxPlusTechnique: [null, [Validators.required]],
    maxPlusPunching: [null, [Validators.required]],
    maxPlusCatching: [null, [Validators.required]],
    highBallBonus: [null, [Validators.required]],
    lowBallBonus: [null, [Validators.required]],
    isDropOnly: [null, [Validators.required]],
    sellCoinGroupNum: [null, [Validators.required]],
    sellMedalId: [],
    characterBookId: [null, [Validators.required]],
    bookNo: [null, [Validators.required]],
    isShowBook: [null, [Validators.required]],
    levelGroupId: [null, [Validators.required]],
    startAt: [],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mPlayableCardService: MPlayableCardService,
    protected mModelCardService: MModelCardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mPlayableCard }) => {
      this.updateForm(mPlayableCard);
      this.mPlayableCard = mPlayableCard;
    });
    this.mModelCardService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMModelCard[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMModelCard[]>) => response.body)
      )
      .subscribe((res: IMModelCard[]) => (this.mmodelcards = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mPlayableCard: IMPlayableCard) {
    this.editForm.patchValue({
      id: mPlayableCard.id,
      modelId: mPlayableCard.modelId,
      properPositionGk: mPlayableCard.properPositionGk,
      properPositionFw: mPlayableCard.properPositionFw,
      properPositionOmf: mPlayableCard.properPositionOmf,
      properPositionDmf: mPlayableCard.properPositionDmf,
      properPositionDf: mPlayableCard.properPositionDf,
      characterId: mPlayableCard.characterId,
      nickName: mPlayableCard.nickName,
      teamId: mPlayableCard.teamId,
      nationalityId: mPlayableCard.nationalityId,
      rarity: mPlayableCard.rarity,
      attribute: mPlayableCard.attribute,
      thumbnailAssetsId: mPlayableCard.thumbnailAssetsId,
      cardIllustAssetsId: mPlayableCard.cardIllustAssetsId,
      playableAssetsId: mPlayableCard.playableAssetsId,
      teamEffectId: mPlayableCard.teamEffectId,
      triggerEffectId: mPlayableCard.triggerEffectId,
      maxActionSlot: mPlayableCard.maxActionSlot,
      initialActionId1: mPlayableCard.initialActionId1,
      initialActionId2: mPlayableCard.initialActionId2,
      initialActionId3: mPlayableCard.initialActionId3,
      initialActionId4: mPlayableCard.initialActionId4,
      initialActionId5: mPlayableCard.initialActionId5,
      initialStamina: mPlayableCard.initialStamina,
      initialDribble: mPlayableCard.initialDribble,
      initialShoot: mPlayableCard.initialShoot,
      initialPass: mPlayableCard.initialPass,
      initialTackle: mPlayableCard.initialTackle,
      initialBlock: mPlayableCard.initialBlock,
      initialIntercept: mPlayableCard.initialIntercept,
      initialSpeed: mPlayableCard.initialSpeed,
      initialPower: mPlayableCard.initialPower,
      initialTechnique: mPlayableCard.initialTechnique,
      initialPunching: mPlayableCard.initialPunching,
      initialCatching: mPlayableCard.initialCatching,
      maxStamina: mPlayableCard.maxStamina,
      maxDribble: mPlayableCard.maxDribble,
      maxShoot: mPlayableCard.maxShoot,
      maxPass: mPlayableCard.maxPass,
      maxTackle: mPlayableCard.maxTackle,
      maxBlock: mPlayableCard.maxBlock,
      maxIntercept: mPlayableCard.maxIntercept,
      maxSpeed: mPlayableCard.maxSpeed,
      maxPower: mPlayableCard.maxPower,
      maxTechnique: mPlayableCard.maxTechnique,
      maxPunching: mPlayableCard.maxPunching,
      maxCatching: mPlayableCard.maxCatching,
      maxPlusDribble: mPlayableCard.maxPlusDribble,
      maxPlusShoot: mPlayableCard.maxPlusShoot,
      maxPlusPass: mPlayableCard.maxPlusPass,
      maxPlusTackle: mPlayableCard.maxPlusTackle,
      maxPlusBlock: mPlayableCard.maxPlusBlock,
      maxPlusIntercept: mPlayableCard.maxPlusIntercept,
      maxPlusSpeed: mPlayableCard.maxPlusSpeed,
      maxPlusPower: mPlayableCard.maxPlusPower,
      maxPlusTechnique: mPlayableCard.maxPlusTechnique,
      maxPlusPunching: mPlayableCard.maxPlusPunching,
      maxPlusCatching: mPlayableCard.maxPlusCatching,
      highBallBonus: mPlayableCard.highBallBonus,
      lowBallBonus: mPlayableCard.lowBallBonus,
      isDropOnly: mPlayableCard.isDropOnly,
      sellCoinGroupNum: mPlayableCard.sellCoinGroupNum,
      sellMedalId: mPlayableCard.sellMedalId,
      characterBookId: mPlayableCard.characterBookId,
      bookNo: mPlayableCard.bookNo,
      isShowBook: mPlayableCard.isShowBook,
      levelGroupId: mPlayableCard.levelGroupId,
      startAt: mPlayableCard.startAt,
      idId: mPlayableCard.idId
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
    const mPlayableCard = this.createFromForm();
    if (mPlayableCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mPlayableCardService.update(mPlayableCard));
    } else {
      this.subscribeToSaveResponse(this.mPlayableCardService.create(mPlayableCard));
    }
  }

  private createFromForm(): IMPlayableCard {
    const entity = {
      ...new MPlayableCard(),
      id: this.editForm.get(['id']).value,
      modelId: this.editForm.get(['modelId']).value,
      properPositionGk: this.editForm.get(['properPositionGk']).value,
      properPositionFw: this.editForm.get(['properPositionFw']).value,
      properPositionOmf: this.editForm.get(['properPositionOmf']).value,
      properPositionDmf: this.editForm.get(['properPositionDmf']).value,
      properPositionDf: this.editForm.get(['properPositionDf']).value,
      characterId: this.editForm.get(['characterId']).value,
      nickName: this.editForm.get(['nickName']).value,
      teamId: this.editForm.get(['teamId']).value,
      nationalityId: this.editForm.get(['nationalityId']).value,
      rarity: this.editForm.get(['rarity']).value,
      attribute: this.editForm.get(['attribute']).value,
      thumbnailAssetsId: this.editForm.get(['thumbnailAssetsId']).value,
      cardIllustAssetsId: this.editForm.get(['cardIllustAssetsId']).value,
      playableAssetsId: this.editForm.get(['playableAssetsId']).value,
      teamEffectId: this.editForm.get(['teamEffectId']).value,
      triggerEffectId: this.editForm.get(['triggerEffectId']).value,
      maxActionSlot: this.editForm.get(['maxActionSlot']).value,
      initialActionId1: this.editForm.get(['initialActionId1']).value,
      initialActionId2: this.editForm.get(['initialActionId2']).value,
      initialActionId3: this.editForm.get(['initialActionId3']).value,
      initialActionId4: this.editForm.get(['initialActionId4']).value,
      initialActionId5: this.editForm.get(['initialActionId5']).value,
      initialStamina: this.editForm.get(['initialStamina']).value,
      initialDribble: this.editForm.get(['initialDribble']).value,
      initialShoot: this.editForm.get(['initialShoot']).value,
      initialPass: this.editForm.get(['initialPass']).value,
      initialTackle: this.editForm.get(['initialTackle']).value,
      initialBlock: this.editForm.get(['initialBlock']).value,
      initialIntercept: this.editForm.get(['initialIntercept']).value,
      initialSpeed: this.editForm.get(['initialSpeed']).value,
      initialPower: this.editForm.get(['initialPower']).value,
      initialTechnique: this.editForm.get(['initialTechnique']).value,
      initialPunching: this.editForm.get(['initialPunching']).value,
      initialCatching: this.editForm.get(['initialCatching']).value,
      maxStamina: this.editForm.get(['maxStamina']).value,
      maxDribble: this.editForm.get(['maxDribble']).value,
      maxShoot: this.editForm.get(['maxShoot']).value,
      maxPass: this.editForm.get(['maxPass']).value,
      maxTackle: this.editForm.get(['maxTackle']).value,
      maxBlock: this.editForm.get(['maxBlock']).value,
      maxIntercept: this.editForm.get(['maxIntercept']).value,
      maxSpeed: this.editForm.get(['maxSpeed']).value,
      maxPower: this.editForm.get(['maxPower']).value,
      maxTechnique: this.editForm.get(['maxTechnique']).value,
      maxPunching: this.editForm.get(['maxPunching']).value,
      maxCatching: this.editForm.get(['maxCatching']).value,
      maxPlusDribble: this.editForm.get(['maxPlusDribble']).value,
      maxPlusShoot: this.editForm.get(['maxPlusShoot']).value,
      maxPlusPass: this.editForm.get(['maxPlusPass']).value,
      maxPlusTackle: this.editForm.get(['maxPlusTackle']).value,
      maxPlusBlock: this.editForm.get(['maxPlusBlock']).value,
      maxPlusIntercept: this.editForm.get(['maxPlusIntercept']).value,
      maxPlusSpeed: this.editForm.get(['maxPlusSpeed']).value,
      maxPlusPower: this.editForm.get(['maxPlusPower']).value,
      maxPlusTechnique: this.editForm.get(['maxPlusTechnique']).value,
      maxPlusPunching: this.editForm.get(['maxPlusPunching']).value,
      maxPlusCatching: this.editForm.get(['maxPlusCatching']).value,
      highBallBonus: this.editForm.get(['highBallBonus']).value,
      lowBallBonus: this.editForm.get(['lowBallBonus']).value,
      isDropOnly: this.editForm.get(['isDropOnly']).value,
      sellCoinGroupNum: this.editForm.get(['sellCoinGroupNum']).value,
      sellMedalId: this.editForm.get(['sellMedalId']).value,
      characterBookId: this.editForm.get(['characterBookId']).value,
      bookNo: this.editForm.get(['bookNo']).value,
      isShowBook: this.editForm.get(['isShowBook']).value,
      levelGroupId: this.editForm.get(['levelGroupId']).value,
      startAt: this.editForm.get(['startAt']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMPlayableCard>>) {
    result.subscribe((res: HttpResponse<IMPlayableCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMModelCardById(index: number, item: IMModelCard) {
    return item.id;
  }
}
