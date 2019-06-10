export interface IMGachaRenditionAfterInputCutIn {
  id?: number;
  renditionId?: number;
  isSsr?: number;
  weight?: number;
  cutInBgPrefabName?: any;
  seStartCutIn?: any;
}

export class MGachaRenditionAfterInputCutIn implements IMGachaRenditionAfterInputCutIn {
  constructor(
    public id?: number,
    public renditionId?: number,
    public isSsr?: number,
    public weight?: number,
    public cutInBgPrefabName?: any,
    public seStartCutIn?: any
  ) {}
}
