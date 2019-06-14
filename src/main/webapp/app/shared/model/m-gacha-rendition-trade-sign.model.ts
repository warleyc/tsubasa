export interface IMGachaRenditionTradeSign {
  id?: number;
  renditionId?: number;
  isSsr?: number;
  weight?: number;
  signTextureName?: any;
}

export class MGachaRenditionTradeSign implements IMGachaRenditionTradeSign {
  constructor(
    public id?: number,
    public renditionId?: number,
    public isSsr?: number,
    public weight?: number,
    public signTextureName?: any
  ) {}
}
