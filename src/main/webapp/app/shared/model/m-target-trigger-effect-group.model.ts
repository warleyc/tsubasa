export interface IMTargetTriggerEffectGroup {
  id?: number;
  groupId?: number;
  triggerEffectId?: number;
  mtriggereffectbaseId?: number;
}

export class MTargetTriggerEffectGroup implements IMTargetTriggerEffectGroup {
  constructor(public id?: number, public groupId?: number, public triggerEffectId?: number, public mtriggereffectbaseId?: number) {}
}
