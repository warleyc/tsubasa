export interface IMTips {
  id?: number;
  priority?: number;
  title?: any;
  description?: any;
  imageAssetName?: any;
  colorType?: number;
  isTips?: number;
  startAt?: number;
  endAt?: number;
}

export class MTips implements IMTips {
  constructor(
    public id?: number,
    public priority?: number,
    public title?: any,
    public description?: any,
    public imageAssetName?: any,
    public colorType?: number,
    public isTips?: number,
    public startAt?: number,
    public endAt?: number
  ) {}
}
