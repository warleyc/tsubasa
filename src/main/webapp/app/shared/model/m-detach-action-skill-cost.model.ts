export interface IMDetachActionSkillCost {
  id?: number;
  rarity?: number;
  cost?: number;
}

export class MDetachActionSkillCost implements IMDetachActionSkillCost {
  constructor(public id?: number, public rarity?: number, public cost?: number) {}
}
