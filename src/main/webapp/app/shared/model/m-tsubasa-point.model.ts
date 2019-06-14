export interface IMTsubasaPoint {
  id?: number;
  matchType?: number;
  pointType?: number;
  calcType?: number;
  aValue?: number;
  bValue?: number;
  orderNum?: number;
}

export class MTsubasaPoint implements IMTsubasaPoint {
  constructor(
    public id?: number,
    public matchType?: number,
    public pointType?: number,
    public calcType?: number,
    public aValue?: number,
    public bValue?: number,
    public orderNum?: number
  ) {}
}
