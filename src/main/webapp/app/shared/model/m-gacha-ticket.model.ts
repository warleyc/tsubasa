export interface IMGachaTicket {
  id?: number;
  name?: any;
  shortName?: any;
  description?: any;
  maxAmount?: number;
  thumbnailAssetName?: any;
  endAt?: number;
}

export class MGachaTicket implements IMGachaTicket {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public description?: any,
    public maxAmount?: number,
    public thumbnailAssetName?: any,
    public endAt?: number
  ) {}
}
