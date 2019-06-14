import { IMTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

export interface IMTriggerEffectBase {
  id?: number;
  name?: any;
  rarity?: number;
  effectValue?: number;
  triggerProbability?: number;
  targetCardParameter?: number;
  effectId?: number;
  description?: any;
  mtriggereffectrangeId?: number;
  mTargetTriggerEffectGroups?: IMTargetTriggerEffectGroup[];
}

export class MTriggerEffectBase implements IMTriggerEffectBase {
  constructor(
    public id?: number,
    public name?: any,
    public rarity?: number,
    public effectValue?: number,
    public triggerProbability?: number,
    public targetCardParameter?: number,
    public effectId?: number,
    public description?: any,
    public mtriggereffectrangeId?: number,
    public mTargetTriggerEffectGroups?: IMTargetTriggerEffectGroup[]
  ) {}
}
