export interface IMPenaltyKickCut {
  id?: number;
  kickerCourse?: number;
  keeperCourse?: number;
  keeperCommand?: number;
  resultType?: number;
  offenseSequenceText?: any;
  defenseSequenceText?: any;
}

export class MPenaltyKickCut implements IMPenaltyKickCut {
  constructor(
    public id?: number,
    public kickerCourse?: number,
    public keeperCourse?: number,
    public keeperCommand?: number,
    public resultType?: number,
    public offenseSequenceText?: any,
    public defenseSequenceText?: any
  ) {}
}
