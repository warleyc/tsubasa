export interface IMMarathonBoostItem {
  id?: number;
  eventId?: number;
  boostRatio?: number;
  name?: any;
  shortName?: any;
  thumbnailAssetName?: any;
  description?: any;
}

export class MMarathonBoostItem implements IMMarathonBoostItem {
  constructor(
    public id?: number,
    public eventId?: number,
    public boostRatio?: number,
    public name?: any,
    public shortName?: any,
    public thumbnailAssetName?: any,
    public description?: any
  ) {}
}
