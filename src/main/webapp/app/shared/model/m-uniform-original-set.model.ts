export interface IMUniformOriginalSet {
  id?: number;
  name?: any;
  shortName?: any;
  menuName?: any;
  upModelId?: number;
  bottomModelId?: number;
  thumbnailAssetName?: any;
  uniformType?: number;
  isDefault?: number;
  orderNum?: number;
  description?: any;
}

export class MUniformOriginalSet implements IMUniformOriginalSet {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public menuName?: any,
    public upModelId?: number,
    public bottomModelId?: number,
    public thumbnailAssetName?: any,
    public uniformType?: number,
    public isDefault?: number,
    public orderNum?: number,
    public description?: any
  ) {}
}
