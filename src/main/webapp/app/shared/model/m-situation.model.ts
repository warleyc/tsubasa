export interface IMSituation {
  id?: number;
  kickoff?: number;
  penaltyKick?: number;
  matchInterval?: number;
  outOfPlay?: number;
  foul?: number;
  goal?: number;
  score?: number;
  time?: number;
}

export class MSituation implements IMSituation {
  constructor(
    public id?: number,
    public kickoff?: number,
    public penaltyKick?: number,
    public matchInterval?: number,
    public outOfPlay?: number,
    public foul?: number,
    public goal?: number,
    public score?: number,
    public time?: number
  ) {}
}
