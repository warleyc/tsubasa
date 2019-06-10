export interface IMDistributeParamPoint {
  id?: number;
  targetAttribute?: number;
  targetNationalityGroupId?: number;
  name?: any;
  description?: any;
  thumbnailAssetName?: any;
  iconAssetName?: any;
}

export class MDistributeParamPoint implements IMDistributeParamPoint {
  constructor(
    public id?: number,
    public targetAttribute?: number,
    public targetNationalityGroupId?: number,
    public name?: any,
    public description?: any,
    public thumbnailAssetName?: any,
    public iconAssetName?: any
  ) {}
}
