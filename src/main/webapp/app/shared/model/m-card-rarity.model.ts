export interface IMCardRarity {
  id?: number;
  rarity?: number;
  name?: any;
}

export class MCardRarity implements IMCardRarity {
  constructor(public id?: number, public rarity?: number, public name?: any) {}
}
