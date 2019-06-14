export interface IMTeamEffectBase {
  id?: number;
  name?: any;
  rarity?: number;
  effectValueMin?: number;
  effectValueMax?: number;
  triggerProbabilityMin?: number;
  triggerProbabilityMax?: number;
  effectId?: number;
  effectValueMin2?: number;
  effectValueMax2?: number;
  triggerProbabilityMin2?: number;
  triggerProbabilityMax2?: number;
  effectId2?: number;
  description?: any;
  idId?: number;
}

export class MTeamEffectBase implements IMTeamEffectBase {
  constructor(
    public id?: number,
    public name?: any,
    public rarity?: number,
    public effectValueMin?: number,
    public effectValueMax?: number,
    public triggerProbabilityMin?: number,
    public triggerProbabilityMax?: number,
    public effectId?: number,
    public effectValueMin2?: number,
    public effectValueMax2?: number,
    public triggerProbabilityMin2?: number,
    public triggerProbabilityMax2?: number,
    public effectId2?: number,
    public description?: any,
    public idId?: number
  ) {}
}
