export interface IMActionRarity {
  id?: number;
  rarity?: number;
  name?: any;
}

export class MActionRarity implements IMActionRarity {
  constructor(public id?: number, public rarity?: number, public name?: any) {}
}
