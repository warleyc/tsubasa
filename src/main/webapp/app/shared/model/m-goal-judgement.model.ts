export interface IMGoalJudgement {
  id?: number;
  judgementId?: number;
  encountersType?: number;
  successRate?: number;
  goalPostRate?: number;
  ballPushRate?: number;
  clearRate?: number;
}

export class MGoalJudgement implements IMGoalJudgement {
  constructor(
    public id?: number,
    public judgementId?: number,
    public encountersType?: number,
    public successRate?: number,
    public goalPostRate?: number,
    public ballPushRate?: number,
    public clearRate?: number
  ) {}
}
