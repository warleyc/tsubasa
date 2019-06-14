export interface IMGuildChatDefaultStamp {
  id?: number;
  masterId?: number;
}

export class MGuildChatDefaultStamp implements IMGuildChatDefaultStamp {
  constructor(public id?: number, public masterId?: number) {}
}
