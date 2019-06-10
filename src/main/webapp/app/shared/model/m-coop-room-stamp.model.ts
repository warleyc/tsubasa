export interface IMCoopRoomStamp {
  id?: number;
  role?: number;
  orderNum?: number;
  masterId?: number;
}

export class MCoopRoomStamp implements IMCoopRoomStamp {
  constructor(public id?: number, public role?: number, public orderNum?: number, public masterId?: number) {}
}
