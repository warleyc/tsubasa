export interface IMGuildNegativeWord {
  id?: number;
  word?: any;
}

export class MGuildNegativeWord implements IMGuildNegativeWord {
  constructor(public id?: number, public word?: any) {}
}
