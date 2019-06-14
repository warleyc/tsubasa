export interface IMTutorialMessage {
  id?: number;
  step?: number;
  orderNum?: number;
  position?: number;
  message?: any;
}

export class MTutorialMessage implements IMTutorialMessage {
  constructor(public id?: number, public step?: number, public orderNum?: number, public position?: number, public message?: any) {}
}
