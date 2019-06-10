export interface IMCutShootOrbit {
  id?: number;
  shootOrbit?: number;
  shootResult?: number;
  offenseSequenceText?: any;
  defenseSequenceText?: any;
}

export class MCutShootOrbit implements IMCutShootOrbit {
  constructor(
    public id?: number,
    public shootOrbit?: number,
    public shootResult?: number,
    public offenseSequenceText?: any,
    public defenseSequenceText?: any
  ) {}
}
