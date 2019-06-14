export interface IMModelUniformBottom {
  id?: number;
  uniformBottomId?: number;
  defaultDressingType?: number;
  uniformModel?: number;
  uniformTexture?: number;
  uniformNoTexture?: number;
  defaultColor?: any;
  uniformNoColor?: any;
  numberRightLeg?: number;
  numberLeftLeg?: number;
  uniformNoPositionX?: number;
  uniformNoPositionY?: number;
}

export class MModelUniformBottom implements IMModelUniformBottom {
  constructor(
    public id?: number,
    public uniformBottomId?: number,
    public defaultDressingType?: number,
    public uniformModel?: number,
    public uniformTexture?: number,
    public uniformNoTexture?: number,
    public defaultColor?: any,
    public uniformNoColor?: any,
    public numberRightLeg?: number,
    public numberLeftLeg?: number,
    public uniformNoPositionX?: number,
    public uniformNoPositionY?: number
  ) {}
}
