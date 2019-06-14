export interface IMMatchFormation {
  id?: number;
  position1?: number;
  position2?: number;
  position3?: number;
  position4?: number;
  position5?: number;
  position6?: number;
  position7?: number;
  position8?: number;
  position9?: number;
  position10?: number;
  position11?: number;
}

export class MMatchFormation implements IMMatchFormation {
  constructor(
    public id?: number,
    public position1?: number,
    public position2?: number,
    public position3?: number,
    public position4?: number,
    public position5?: number,
    public position6?: number,
    public position7?: number,
    public position8?: number,
    public position9?: number,
    public position10?: number,
    public position11?: number
  ) {}
}
