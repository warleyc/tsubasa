export interface IMLuckRateGroup {
  id?: number;
  groupId?: number;
  rarity?: number;
  rate?: number;
}

export class MLuckRateGroup implements IMLuckRateGroup {
  constructor(public id?: number, public groupId?: number, public rarity?: number, public rate?: number) {}
}
