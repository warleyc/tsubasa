export interface IMTeamEffectRarity {
  id?: number;
  rarity?: number;
  name?: any;
  maxLevel?: number;
}

export class MTeamEffectRarity implements IMTeamEffectRarity {
  constructor(public id?: number, public rarity?: number, public name?: any, public maxLevel?: number) {}
}
