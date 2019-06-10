export interface IMEncountersCut {
  id?: number;
  condition?: number;
  ballFloatCondition?: number;
  command1Type?: number;
  command1IsSkill?: number;
  command2Type?: number;
  command2IsSkill?: number;
  result?: number;
  shootResult?: number;
  isThrown?: number;
  offenseSequenceText?: any;
  defenseSequenceText?: any;
}

export class MEncountersCut implements IMEncountersCut {
  constructor(
    public id?: number,
    public condition?: number,
    public ballFloatCondition?: number,
    public command1Type?: number,
    public command1IsSkill?: number,
    public command2Type?: number,
    public command2IsSkill?: number,
    public result?: number,
    public shootResult?: number,
    public isThrown?: number,
    public offenseSequenceText?: any,
    public defenseSequenceText?: any
  ) {}
}
