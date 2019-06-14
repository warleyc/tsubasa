export interface IMTargetRarityGroup {
  id?: number;
  groupId?: number;
  cardRarity?: number;
}

export class MTargetRarityGroup implements IMTargetRarityGroup {
  constructor(public id?: number, public groupId?: number, public cardRarity?: number) {}
}
