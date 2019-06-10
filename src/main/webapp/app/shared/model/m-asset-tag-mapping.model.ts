export interface IMAssetTagMapping {
  id?: number;
  tag?: number;
  ids?: any;
}

export class MAssetTagMapping implements IMAssetTagMapping {
  constructor(public id?: number, public tag?: number, public ids?: any) {}
}
