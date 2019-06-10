export interface IMCheatCaution {
  id?: number;
  caution?: number;
  description?: any;
  imageAssetName?: any;
}

export class MCheatCaution implements IMCheatCaution {
  constructor(public id?: number, public caution?: number, public description?: any, public imageAssetName?: any) {}
}
