export interface IMGachaRenditionTrajectoryCutIn {
  id?: number;
  renditionId?: number;
  trajectoryType?: number;
  spriteName?: any;
  weight?: number;
  voice?: any;
  exceptKickerId?: number;
}

export class MGachaRenditionTrajectoryCutIn implements IMGachaRenditionTrajectoryCutIn {
  constructor(
    public id?: number,
    public renditionId?: number,
    public trajectoryType?: number,
    public spriteName?: any,
    public weight?: number,
    public voice?: any,
    public exceptKickerId?: number
  ) {}
}
