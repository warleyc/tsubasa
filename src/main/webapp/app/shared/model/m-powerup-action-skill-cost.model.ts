export interface IMPowerupActionSkillCost {
  id?: number;
  actionRarity?: number;
  actionLevel?: number;
  cost?: number;
}

export class MPowerupActionSkillCost implements IMPowerupActionSkillCost {
  constructor(public id?: number, public actionRarity?: number, public actionLevel?: number, public cost?: number) {}
}
