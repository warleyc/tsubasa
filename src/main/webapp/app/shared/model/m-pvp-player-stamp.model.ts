export interface IMPvpPlayerStamp {
  id?: number;
  orderNum?: number;
  masterId?: number;
}

export class MPvpPlayerStamp implements IMPvpPlayerStamp {
  constructor(public id?: number, public orderNum?: number, public masterId?: number) {}
}
