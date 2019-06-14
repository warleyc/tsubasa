export interface IMNpcCard {
  id?: number;
  characterId?: number;
  shortName?: any;
  nickName?: any;
  teamId?: number;
  nationalityId?: number;
  rarity?: number;
  attribute?: number;
  modelId?: number;
  level?: number;
  thumbnailAssetsId?: number;
  cardIllustAssetsId?: number;
  playableAssetsId?: number;
  teamEffectId?: number;
  teamEffectLevel?: number;
  triggerEffectId?: number;
  action1Id?: number;
  action1Level?: number;
  action2Id?: number;
  action2Level?: number;
  action3Id?: number;
  action3Level?: number;
  action4Id?: number;
  action4Level?: number;
  action5Id?: number;
  action5Level?: number;
  stamina?: number;
  dribble?: number;
  shoot?: number;
  ballPass?: number;
  tackle?: number;
  block?: number;
  intercept?: number;
  speed?: number;
  power?: number;
  technique?: number;
  punching?: number;
  catching?: number;
  highBallBonus?: number;
  lowBallBonus?: number;
  personalityId?: number;
  uniformNo?: number;
  levelGroupId?: number;
  idId?: number;
}

export class MNpcCard implements IMNpcCard {
  constructor(
    public id?: number,
    public characterId?: number,
    public shortName?: any,
    public nickName?: any,
    public teamId?: number,
    public nationalityId?: number,
    public rarity?: number,
    public attribute?: number,
    public modelId?: number,
    public level?: number,
    public thumbnailAssetsId?: number,
    public cardIllustAssetsId?: number,
    public playableAssetsId?: number,
    public teamEffectId?: number,
    public teamEffectLevel?: number,
    public triggerEffectId?: number,
    public action1Id?: number,
    public action1Level?: number,
    public action2Id?: number,
    public action2Level?: number,
    public action3Id?: number,
    public action3Level?: number,
    public action4Id?: number,
    public action4Level?: number,
    public action5Id?: number,
    public action5Level?: number,
    public stamina?: number,
    public dribble?: number,
    public shoot?: number,
    public ballPass?: number,
    public tackle?: number,
    public block?: number,
    public intercept?: number,
    public speed?: number,
    public power?: number,
    public technique?: number,
    public punching?: number,
    public catching?: number,
    public highBallBonus?: number,
    public lowBallBonus?: number,
    public personalityId?: number,
    public uniformNo?: number,
    public levelGroupId?: number,
    public idId?: number
  ) {}
}
