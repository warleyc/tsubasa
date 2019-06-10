export interface IMGachaRenditionExtraCutin {
  id?: number;
  renditionId?: number;
  mainPrefabName?: any;
  voiceStartCutIn?: any;
  serif?: any;
}

export class MGachaRenditionExtraCutin implements IMGachaRenditionExtraCutin {
  constructor(
    public id?: number,
    public renditionId?: number,
    public mainPrefabName?: any,
    public voiceStartCutIn?: any,
    public serif?: any
  ) {}
}
