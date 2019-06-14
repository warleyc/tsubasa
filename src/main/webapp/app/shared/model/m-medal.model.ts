export interface IMMedal {
  id?: number;
  medalType?: number;
  name?: any;
  description?: any;
  maxAmount?: number;
  iconAssetName?: any;
  thumbnailAssetName?: any;
}

export class MMedal implements IMMedal {
  constructor(
    public id?: number,
    public medalType?: number,
    public name?: any,
    public description?: any,
    public maxAmount?: number,
    public iconAssetName?: any,
    public thumbnailAssetName?: any
  ) {}
}
