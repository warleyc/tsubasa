export interface IMInheritActionSkillCost {
  id?: number;
  rarity?: number;
  level?: number;
  cost?: number;
}

export class MInheritActionSkillCost implements IMInheritActionSkillCost {
  constructor(public id?: number, public rarity?: number, public level?: number, public cost?: number) {}
}
