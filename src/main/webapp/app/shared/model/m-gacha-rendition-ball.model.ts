export interface IMGachaRenditionBall {
  id?: number;
  renditionId?: number;
  isSsr?: number;
  weight?: number;
  ballTextureName?: any;
  trajectoryNormalTextureName?: any;
  trajectoryGoldTextureName?: any;
  trajectoryRainbowTextureName?: any;
}

export class MGachaRenditionBall implements IMGachaRenditionBall {
  constructor(
    public id?: number,
    public renditionId?: number,
    public isSsr?: number,
    public weight?: number,
    public ballTextureName?: any,
    public trajectoryNormalTextureName?: any,
    public trajectoryGoldTextureName?: any,
    public trajectoryRainbowTextureName?: any
  ) {}
}
