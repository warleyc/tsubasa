export interface IMFullPowerPoint {
  id?: number;
  pointType?: number;
  value?: number;
}

export class MFullPowerPoint implements IMFullPowerPoint {
  constructor(public id?: number, public pointType?: number, public value?: number) {}
}
