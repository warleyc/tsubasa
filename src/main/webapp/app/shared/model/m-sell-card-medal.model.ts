export interface IMSellCardMedal {
  id?: number;
  medalId?: number;
  amount?: number;
}

export class MSellCardMedal implements IMSellCardMedal {
  constructor(public id?: number, public medalId?: number, public amount?: number) {}
}
