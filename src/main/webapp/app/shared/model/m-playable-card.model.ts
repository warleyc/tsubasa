import { IMArousal } from 'app/shared/model/m-arousal.model';
import { IMTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

export interface IMPlayableCard {
  id?: number;
  modelId?: number;
  properPositionGk?: number;
  properPositionFw?: number;
  properPositionOmf?: number;
  properPositionDmf?: number;
  properPositionDf?: number;
  characterId?: number;
  nickName?: any;
  teamId?: number;
  nationalityId?: number;
  rarity?: number;
  attribute?: number;
  thumbnailAssetsId?: number;
  cardIllustAssetsId?: number;
  playableAssetsId?: number;
  teamEffectId?: number;
  triggerEffectId?: number;
  maxActionSlot?: number;
  initialActionId1?: number;
  initialActionId2?: number;
  initialActionId3?: number;
  initialActionId4?: number;
  initialActionId5?: number;
  initialStamina?: number;
  initialDribble?: number;
  initialShoot?: number;
  initialPass?: number;
  initialTackle?: number;
  initialBlock?: number;
  initialIntercept?: number;
  initialSpeed?: number;
  initialPower?: number;
  initialTechnique?: number;
  initialPunching?: number;
  initialCatching?: number;
  maxStamina?: number;
  maxDribble?: number;
  maxShoot?: number;
  maxPass?: number;
  maxTackle?: number;
  maxBlock?: number;
  maxIntercept?: number;
  maxSpeed?: number;
  maxPower?: number;
  maxTechnique?: number;
  maxPunching?: number;
  maxCatching?: number;
  maxPlusDribble?: number;
  maxPlusShoot?: number;
  maxPlusPass?: number;
  maxPlusTackle?: number;
  maxPlusBlock?: number;
  maxPlusIntercept?: number;
  maxPlusSpeed?: number;
  maxPlusPower?: number;
  maxPlusTechnique?: number;
  maxPlusPunching?: number;
  maxPlusCatching?: number;
  highBallBonus?: number;
  lowBallBonus?: number;
  isDropOnly?: number;
  sellCoinGroupNum?: number;
  sellMedalId?: number;
  characterBookId?: number;
  bookNo?: number;
  isShowBook?: number;
  levelGroupId?: number;
  startAt?: number;
  mmodelcardId?: number;
  mArousals?: IMArousal[];
  mTargetPlayableCardGroups?: IMTargetPlayableCardGroup[];
}

export class MPlayableCard implements IMPlayableCard {
  constructor(
    public id?: number,
    public modelId?: number,
    public properPositionGk?: number,
    public properPositionFw?: number,
    public properPositionOmf?: number,
    public properPositionDmf?: number,
    public properPositionDf?: number,
    public characterId?: number,
    public nickName?: any,
    public teamId?: number,
    public nationalityId?: number,
    public rarity?: number,
    public attribute?: number,
    public thumbnailAssetsId?: number,
    public cardIllustAssetsId?: number,
    public playableAssetsId?: number,
    public teamEffectId?: number,
    public triggerEffectId?: number,
    public maxActionSlot?: number,
    public initialActionId1?: number,
    public initialActionId2?: number,
    public initialActionId3?: number,
    public initialActionId4?: number,
    public initialActionId5?: number,
    public initialStamina?: number,
    public initialDribble?: number,
    public initialShoot?: number,
    public initialPass?: number,
    public initialTackle?: number,
    public initialBlock?: number,
    public initialIntercept?: number,
    public initialSpeed?: number,
    public initialPower?: number,
    public initialTechnique?: number,
    public initialPunching?: number,
    public initialCatching?: number,
    public maxStamina?: number,
    public maxDribble?: number,
    public maxShoot?: number,
    public maxPass?: number,
    public maxTackle?: number,
    public maxBlock?: number,
    public maxIntercept?: number,
    public maxSpeed?: number,
    public maxPower?: number,
    public maxTechnique?: number,
    public maxPunching?: number,
    public maxCatching?: number,
    public maxPlusDribble?: number,
    public maxPlusShoot?: number,
    public maxPlusPass?: number,
    public maxPlusTackle?: number,
    public maxPlusBlock?: number,
    public maxPlusIntercept?: number,
    public maxPlusSpeed?: number,
    public maxPlusPower?: number,
    public maxPlusTechnique?: number,
    public maxPlusPunching?: number,
    public maxPlusCatching?: number,
    public highBallBonus?: number,
    public lowBallBonus?: number,
    public isDropOnly?: number,
    public sellCoinGroupNum?: number,
    public sellMedalId?: number,
    public characterBookId?: number,
    public bookNo?: number,
    public isShowBook?: number,
    public levelGroupId?: number,
    public startAt?: number,
    public mmodelcardId?: number,
    public mArousals?: IMArousal[],
    public mTargetPlayableCardGroups?: IMTargetPlayableCardGroup[]
  ) {}
}
