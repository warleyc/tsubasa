export interface IMNgWord {
  id?: number;
  word?: any;
  judgeType?: number;
}

export class MNgWord implements IMNgWord {
  constructor(public id?: number, public word?: any, public judgeType?: number) {}
}
