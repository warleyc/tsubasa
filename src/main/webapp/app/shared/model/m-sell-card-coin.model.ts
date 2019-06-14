export interface IMSellCardCoin {
  id?: number;
  groupNum?: number;
  level?: number;
  coin?: number;
}

export class MSellCardCoin implements IMSellCardCoin {
  constructor(public id?: number, public groupNum?: number, public level?: number, public coin?: number) {}
}
