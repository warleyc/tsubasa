export interface IMPvpWatcherStamp {
  id?: number;
  orderNum?: number;
  masterId?: number;
}

export class MPvpWatcherStamp implements IMPvpWatcherStamp {
  constructor(public id?: number, public orderNum?: number, public masterId?: number) {}
}
