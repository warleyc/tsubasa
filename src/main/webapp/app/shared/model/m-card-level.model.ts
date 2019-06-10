export interface IMCardLevel {
  id?: number;
  rarity?: number;
  level?: number;
  groupId?: number;
  exp?: number;
}

export class MCardLevel implements IMCardLevel {
  constructor(public id?: number, public rarity?: number, public level?: number, public groupId?: number, public exp?: number) {}
}
