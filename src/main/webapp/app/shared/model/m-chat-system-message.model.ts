export interface IMChatSystemMessage {
  id?: number;
  messageType?: number;
  messageKey?: any;
}

export class MChatSystemMessage implements IMChatSystemMessage {
  constructor(public id?: number, public messageType?: number, public messageKey?: any) {}
}
