export interface IMTrainingCost {
  id?: number;
  rarity?: number;
  level?: number;
  cost?: number;
}

export class MTrainingCost implements IMTrainingCost {
  constructor(public id?: number, public rarity?: number, public level?: number, public cost?: number) {}
}
