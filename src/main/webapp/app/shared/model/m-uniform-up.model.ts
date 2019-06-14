export interface IMUniformUp {
  id?: number;
  name?: any;
  shortName?: any;
  menuName?: any;
  modelId?: number;
  thumbnailAssetName?: any;
  uniformType?: number;
  isDefault?: number;
  orderNum?: number;
  description?: any;
}

export class MUniformUp implements IMUniformUp {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public menuName?: any,
    public modelId?: number,
    public thumbnailAssetName?: any,
    public uniformType?: number,
    public isDefault?: number,
    public orderNum?: number,
    public description?: any
  ) {}
}
