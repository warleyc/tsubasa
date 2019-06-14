export interface IMStamp {
  id?: number;
  name?: any;
  thumbnailAsset?: any;
  soundEvent?: any;
  description?: any;
}

export class MStamp implements IMStamp {
  constructor(public id?: number, public name?: any, public thumbnailAsset?: any, public soundEvent?: any, public description?: any) {}
}
