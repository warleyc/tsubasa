export interface IMExtraNews {
  id?: number;
  orderNum?: number;
  assetName?: any;
  webviewId?: any;
  startAt?: number;
  endAt?: number;
}

export class MExtraNews implements IMExtraNews {
  constructor(
    public id?: number,
    public orderNum?: number,
    public assetName?: any,
    public webviewId?: any,
    public startAt?: number,
    public endAt?: number
  ) {}
}
