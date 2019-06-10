import { IMTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

export interface IMAction {
  id?: number;
  name?: any;
  shortName?: any;
  nameRuby?: any;
  description?: any;
  effectDescription?: any;
  initialCommand?: number;
  rarity?: number;
  commandType?: number;
  ballConditionGround?: number;
  ballConditionLow?: number;
  ballConditionHigh?: number;
  staminaLvMin?: number;
  staminaLvMax?: number;
  powerLvMin?: number;
  powerLvMax?: number;
  blowOffCount?: number;
  mShootId?: number;
  foulRate?: number;
  distanceDecayType?: number;
  activateCharacterCount?: number;
  actionCutId?: number;
  powerupGroup?: number;
  mTargetActionGroups?: IMTargetActionGroup[];
}

export class MAction implements IMAction {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public nameRuby?: any,
    public description?: any,
    public effectDescription?: any,
    public initialCommand?: number,
    public rarity?: number,
    public commandType?: number,
    public ballConditionGround?: number,
    public ballConditionLow?: number,
    public ballConditionHigh?: number,
    public staminaLvMin?: number,
    public staminaLvMax?: number,
    public powerLvMin?: number,
    public powerLvMax?: number,
    public blowOffCount?: number,
    public mShootId?: number,
    public foulRate?: number,
    public distanceDecayType?: number,
    public activateCharacterCount?: number,
    public actionCutId?: number,
    public powerupGroup?: number,
    public mTargetActionGroups?: IMTargetActionGroup[]
  ) {}
}
