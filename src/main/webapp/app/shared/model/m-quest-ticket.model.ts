export interface IMQuestTicket {
  id?: number;
  name?: any;
  shortName?: any;
  description?: any;
  thumbnailAsset?: any;
}

export class MQuestTicket implements IMQuestTicket {
  constructor(public id?: number, public name?: any, public shortName?: any, public description?: any, public thumbnailAsset?: any) {}
}
