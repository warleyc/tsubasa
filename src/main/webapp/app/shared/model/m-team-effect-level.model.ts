export interface IMTeamEffectLevel {
  id?: number;
  rarity?: number;
  level?: number;
  exp?: number;
}

export class MTeamEffectLevel implements IMTeamEffectLevel {
  constructor(public id?: number, public rarity?: number, public level?: number, public exp?: number) {}
}
