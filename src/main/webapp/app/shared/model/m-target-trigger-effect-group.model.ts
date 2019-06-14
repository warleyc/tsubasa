export interface IMTargetTriggerEffectGroup {
  id?: number;
  groupId?: number;
  triggerEffectId?: number;
  idId?: number;
}

export class MTargetTriggerEffectGroup implements IMTargetTriggerEffectGroup {
  constructor(public id?: number, public groupId?: number, public triggerEffectId?: number, public idId?: number) {}
}
