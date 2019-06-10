export interface IMEmblemSet {
  id?: number;
  assetName?: any;
  name?: any;
  description?: any;
}

export class MEmblemSet implements IMEmblemSet {
  constructor(public id?: number, public assetName?: any, public name?: any, public description?: any) {}
}
