export interface IMGuildEffect {
  id?: number;
  effectType?: number;
  name?: any;
  effectId?: number;
  thumbnailPath?: any;
}

export class MGuildEffect implements IMGuildEffect {
  constructor(public id?: number, public effectType?: number, public name?: any, public effectId?: number, public thumbnailPath?: any) {}
}
