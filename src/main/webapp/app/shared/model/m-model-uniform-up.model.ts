export interface IMModelUniformUp {
  id?: number;
  uniformUpId?: number;
  defaultDressingType?: number;
  uniformModel?: number;
  uniformTexture?: number;
  uniformNoTexture?: number;
  defaultColor?: any;
  uniformNoColor?: any;
  numberChest?: number;
  numberBelly?: number;
  numberBack?: number;
  uniformNoPositionX?: number;
  uniformNoPositionY?: number;
}

export class MModelUniformUp implements IMModelUniformUp {
  constructor(
    public id?: number,
    public uniformUpId?: number,
    public defaultDressingType?: number,
    public uniformModel?: number,
    public uniformTexture?: number,
    public uniformNoTexture?: number,
    public defaultColor?: any,
    public uniformNoColor?: any,
    public numberChest?: number,
    public numberBelly?: number,
    public numberBack?: number,
    public uniformNoPositionX?: number,
    public uniformNoPositionY?: number
  ) {}
}
