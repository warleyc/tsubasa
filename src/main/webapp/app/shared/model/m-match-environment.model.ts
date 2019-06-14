export interface IMMatchEnvironment {
  id?: number;
  category?: number;
  skyTex?: any;
  ball?: any;
  stadium?: any;
  rainyAssetName?: any;
  isPlayRainySound?: number;
  offenseStartBgm?: any;
  offenseStopBgm?: any;
  defenseStartBgm?: any;
  defenseStopBgm?: any;
}

export class MMatchEnvironment implements IMMatchEnvironment {
  constructor(
    public id?: number,
    public category?: number,
    public skyTex?: any,
    public ball?: any,
    public stadium?: any,
    public rainyAssetName?: any,
    public isPlayRainySound?: number,
    public offenseStartBgm?: any,
    public offenseStopBgm?: any,
    public defenseStartBgm?: any,
    public defenseStopBgm?: any
  ) {}
}
