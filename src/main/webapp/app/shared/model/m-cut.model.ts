export interface IMCut {
  id?: number;
  name?: any;
  fpA?: number;
  fpB?: number;
  fpC?: number;
  fpD?: number;
  fpE?: number;
  fpF?: number;
  gkA?: number;
  gkB?: number;
  showEnvironmentalEffect?: number;
  cutType?: number;
  groupId?: number;
  isCombiCut?: number;
}

export class MCut implements IMCut {
  constructor(
    public id?: number,
    public name?: any,
    public fpA?: number,
    public fpB?: number,
    public fpC?: number,
    public fpD?: number,
    public fpE?: number,
    public fpF?: number,
    public gkA?: number,
    public gkB?: number,
    public showEnvironmentalEffect?: number,
    public cutType?: number,
    public groupId?: number,
    public isCombiCut?: number
  ) {}
}
