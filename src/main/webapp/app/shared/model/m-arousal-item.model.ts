export interface IMArousalItem {
  id?: number;
  name?: any;
  description?: any;
  thumbnailAssetName?: any;
  thumbnailBgAssetName?: any;
  thumbnailFrameAssetName?: any;
  arousalItemType?: number;
}

export class MArousalItem implements IMArousalItem {
  constructor(
    public id?: number,
    public name?: any,
    public description?: any,
    public thumbnailAssetName?: any,
    public thumbnailBgAssetName?: any,
    public thumbnailFrameAssetName?: any,
    public arousalItemType?: number
  ) {}
}
