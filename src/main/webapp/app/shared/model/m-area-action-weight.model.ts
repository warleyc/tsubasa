export interface IMAreaActionWeight {
  id?: number;
  areaType?: number;
  dribbleRate?: number;
  passingRate?: number;
  onetwoRate?: number;
  shootRate?: number;
  volleyShootRate?: number;
  headingShootRate?: number;
  tackleRate?: number;
  blockRate?: number;
  passCutRate?: number;
  clearRate?: number;
  competeRate?: number;
  trapRate?: number;
}

export class MAreaActionWeight implements IMAreaActionWeight {
  constructor(
    public id?: number,
    public areaType?: number,
    public dribbleRate?: number,
    public passingRate?: number,
    public onetwoRate?: number,
    public shootRate?: number,
    public volleyShootRate?: number,
    public headingShootRate?: number,
    public tackleRate?: number,
    public blockRate?: number,
    public passCutRate?: number,
    public clearRate?: number,
    public competeRate?: number,
    public trapRate?: number
  ) {}
}
