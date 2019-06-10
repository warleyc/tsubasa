export interface IMCardIllustAssets {
  id?: number;
  assetName?: any;
  subAssetName?: any;
  offset?: any;
  backgroundAssetName?: any;
  decorationAssetName?: any;
  effectAssetName?: any;
}

export class MCardIllustAssets implements IMCardIllustAssets {
  constructor(
    public id?: number,
    public assetName?: any,
    public subAssetName?: any,
    public offset?: any,
    public backgroundAssetName?: any,
    public decorationAssetName?: any,
    public effectAssetName?: any
  ) {}
}
