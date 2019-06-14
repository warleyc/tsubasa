export interface IMSoundBankEvent {
  id?: number;
  path?: any;
  name?: any;
  eventId?: any;
}

export class MSoundBankEvent implements IMSoundBankEvent {
  constructor(public id?: number, public path?: any, public name?: any, public eventId?: any) {}
}
