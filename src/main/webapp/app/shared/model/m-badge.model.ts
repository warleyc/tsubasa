export interface IMBadge {
  id?: number;
  name?: any;
  type?: number;
  description?: any;
  assetName?: any;
}

export class MBadge implements IMBadge {
  constructor(public id?: number, public name?: any, public type?: number, public description?: any, public assetName?: any) {}
}
