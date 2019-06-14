import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMNpcCard, MNpcCard } from 'app/shared/model/m-npc-card.model';
import { MNpcCardService } from './m-npc-card.service';
import { IMCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from 'app/entities/m-character';

@Component({
  selector: 'jhi-m-npc-card-update',
  templateUrl: './m-npc-card-update.component.html'
})
export class MNpcCardUpdateComponent implements OnInit {
  mNpcCard: IMNpcCard;
  isSaving: boolean;

  mcharacters: IMCharacter[];

  editForm = this.fb.group({
    id: [],
    characterId: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    nickName: [null, [Validators.required]],
    teamId: [null, [Validators.required]],
    nationalityId: [null, [Validators.required]],
    rarity: [null, [Validators.required]],
    attribute: [null, [Validators.required]],
    modelId: [null, [Validators.required]],
    level: [null, [Validators.required]],
    thumbnailAssetsId: [null, [Validators.required]],
    cardIllustAssetsId: [null, [Validators.required]],
    playableAssetsId: [null, [Validators.required]],
    teamEffectId: [],
    teamEffectLevel: [null, [Validators.required]],
    triggerEffectId: [],
    action1Id: [],
    action1Level: [],
    action2Id: [],
    action2Level: [],
    action3Id: [],
    action3Level: [],
    action4Id: [],
    action4Level: [],
    action5Id: [],
    action5Level: [],
    stamina: [null, [Validators.required]],
    dribble: [null, [Validators.required]],
    shoot: [null, [Validators.required]],
    ballPass: [null, [Validators.required]],
    tackle: [null, [Validators.required]],
    block: [null, [Validators.required]],
    intercept: [null, [Validators.required]],
    speed: [null, [Validators.required]],
    power: [null, [Validators.required]],
    technique: [null, [Validators.required]],
    punching: [null, [Validators.required]],
    catching: [null, [Validators.required]],
    highBallBonus: [null, [Validators.required]],
    lowBallBonus: [null, [Validators.required]],
    personalityId: [null, [Validators.required]],
    uniformNo: [null, [Validators.required]],
    levelGroupId: [null, [Validators.required]],
    idId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mNpcCardService: MNpcCardService,
    protected mCharacterService: MCharacterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mNpcCard }) => {
      this.updateForm(mNpcCard);
      this.mNpcCard = mNpcCard;
    });
    this.mCharacterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMCharacter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMCharacter[]>) => response.body)
      )
      .subscribe((res: IMCharacter[]) => (this.mcharacters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mNpcCard: IMNpcCard) {
    this.editForm.patchValue({
      id: mNpcCard.id,
      characterId: mNpcCard.characterId,
      shortName: mNpcCard.shortName,
      nickName: mNpcCard.nickName,
      teamId: mNpcCard.teamId,
      nationalityId: mNpcCard.nationalityId,
      rarity: mNpcCard.rarity,
      attribute: mNpcCard.attribute,
      modelId: mNpcCard.modelId,
      level: mNpcCard.level,
      thumbnailAssetsId: mNpcCard.thumbnailAssetsId,
      cardIllustAssetsId: mNpcCard.cardIllustAssetsId,
      playableAssetsId: mNpcCard.playableAssetsId,
      teamEffectId: mNpcCard.teamEffectId,
      teamEffectLevel: mNpcCard.teamEffectLevel,
      triggerEffectId: mNpcCard.triggerEffectId,
      action1Id: mNpcCard.action1Id,
      action1Level: mNpcCard.action1Level,
      action2Id: mNpcCard.action2Id,
      action2Level: mNpcCard.action2Level,
      action3Id: mNpcCard.action3Id,
      action3Level: mNpcCard.action3Level,
      action4Id: mNpcCard.action4Id,
      action4Level: mNpcCard.action4Level,
      action5Id: mNpcCard.action5Id,
      action5Level: mNpcCard.action5Level,
      stamina: mNpcCard.stamina,
      dribble: mNpcCard.dribble,
      shoot: mNpcCard.shoot,
      ballPass: mNpcCard.ballPass,
      tackle: mNpcCard.tackle,
      block: mNpcCard.block,
      intercept: mNpcCard.intercept,
      speed: mNpcCard.speed,
      power: mNpcCard.power,
      technique: mNpcCard.technique,
      punching: mNpcCard.punching,
      catching: mNpcCard.catching,
      highBallBonus: mNpcCard.highBallBonus,
      lowBallBonus: mNpcCard.lowBallBonus,
      personalityId: mNpcCard.personalityId,
      uniformNo: mNpcCard.uniformNo,
      levelGroupId: mNpcCard.levelGroupId,
      idId: mNpcCard.idId
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
    const mNpcCard = this.createFromForm();
    if (mNpcCard.id !== undefined) {
      this.subscribeToSaveResponse(this.mNpcCardService.update(mNpcCard));
    } else {
      this.subscribeToSaveResponse(this.mNpcCardService.create(mNpcCard));
    }
  }

  private createFromForm(): IMNpcCard {
    const entity = {
      ...new MNpcCard(),
      id: this.editForm.get(['id']).value,
      characterId: this.editForm.get(['characterId']).value,
      shortName: this.editForm.get(['shortName']).value,
      nickName: this.editForm.get(['nickName']).value,
      teamId: this.editForm.get(['teamId']).value,
      nationalityId: this.editForm.get(['nationalityId']).value,
      rarity: this.editForm.get(['rarity']).value,
      attribute: this.editForm.get(['attribute']).value,
      modelId: this.editForm.get(['modelId']).value,
      level: this.editForm.get(['level']).value,
      thumbnailAssetsId: this.editForm.get(['thumbnailAssetsId']).value,
      cardIllustAssetsId: this.editForm.get(['cardIllustAssetsId']).value,
      playableAssetsId: this.editForm.get(['playableAssetsId']).value,
      teamEffectId: this.editForm.get(['teamEffectId']).value,
      teamEffectLevel: this.editForm.get(['teamEffectLevel']).value,
      triggerEffectId: this.editForm.get(['triggerEffectId']).value,
      action1Id: this.editForm.get(['action1Id']).value,
      action1Level: this.editForm.get(['action1Level']).value,
      action2Id: this.editForm.get(['action2Id']).value,
      action2Level: this.editForm.get(['action2Level']).value,
      action3Id: this.editForm.get(['action3Id']).value,
      action3Level: this.editForm.get(['action3Level']).value,
      action4Id: this.editForm.get(['action4Id']).value,
      action4Level: this.editForm.get(['action4Level']).value,
      action5Id: this.editForm.get(['action5Id']).value,
      action5Level: this.editForm.get(['action5Level']).value,
      stamina: this.editForm.get(['stamina']).value,
      dribble: this.editForm.get(['dribble']).value,
      shoot: this.editForm.get(['shoot']).value,
      ballPass: this.editForm.get(['ballPass']).value,
      tackle: this.editForm.get(['tackle']).value,
      block: this.editForm.get(['block']).value,
      intercept: this.editForm.get(['intercept']).value,
      speed: this.editForm.get(['speed']).value,
      power: this.editForm.get(['power']).value,
      technique: this.editForm.get(['technique']).value,
      punching: this.editForm.get(['punching']).value,
      catching: this.editForm.get(['catching']).value,
      highBallBonus: this.editForm.get(['highBallBonus']).value,
      lowBallBonus: this.editForm.get(['lowBallBonus']).value,
      personalityId: this.editForm.get(['personalityId']).value,
      uniformNo: this.editForm.get(['uniformNo']).value,
      levelGroupId: this.editForm.get(['levelGroupId']).value,
      idId: this.editForm.get(['idId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMNpcCard>>) {
    result.subscribe((res: HttpResponse<IMNpcCard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMCharacterById(index: number, item: IMCharacter) {
    return item.id;
  }
}
