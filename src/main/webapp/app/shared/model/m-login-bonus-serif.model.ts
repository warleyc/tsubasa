export interface IMLoginBonusSerif {
  id?: number;
  serifId?: number;
  serif1?: any;
  serif2?: any;
}

export class MLoginBonusSerif implements IMLoginBonusSerif {
  constructor(public id?: number, public serifId?: number, public serif1?: any, public serif2?: any) {}
}
