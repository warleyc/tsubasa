export interface IMGachaRenditionBeforeShootCutInPattern {
  id?: number;
  renditionId?: number;
  isManySsr?: number;
  isSsr?: number;
  weight?: number;
  pattern?: number;
  normalPrefabName?: any;
  flashBackPrefabName1?: any;
  flashBackPrefabName2?: any;
  flashBackPrefabName3?: any;
  flashBackPrefabName4?: any;
  voicePrefix?: any;
  seNormal?: any;
  seFlashBack?: any;
  exceptKickerId?: number;
}

export class MGachaRenditionBeforeShootCutInPattern implements IMGachaRenditionBeforeShootCutInPattern {
  constructor(
    public id?: number,
    public renditionId?: number,
    public isManySsr?: number,
    public isSsr?: number,
    public weight?: number,
    public pattern?: number,
    public normalPrefabName?: any,
    public flashBackPrefabName1?: any,
    public flashBackPrefabName2?: any,
    public flashBackPrefabName3?: any,
    public flashBackPrefabName4?: any,
    public voicePrefix?: any,
    public seNormal?: any,
    public seFlashBack?: any,
    public exceptKickerId?: number
  ) {}
}
