export interface IMActionLevel {
  id?: number;
  rarity?: number;
  level?: number;
  exp?: number;
}

export class MActionLevel implements IMActionLevel {
  constructor(public id?: number, public rarity?: number, public level?: number, public exp?: number) {}
}
