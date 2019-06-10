export interface IMApRecoveryItem {
  id?: number;
  apRecoveryItemType?: number;
  name?: any;
  description?: any;
  thumbnailAssetName?: any;
}

export class MApRecoveryItem implements IMApRecoveryItem {
  constructor(
    public id?: number,
    public apRecoveryItemType?: number,
    public name?: any,
    public description?: any,
    public thumbnailAssetName?: any
  ) {}
}
