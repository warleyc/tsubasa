export interface IMQuestClearRewardWeight {
  id?: number;
  reward1?: number;
  reward2?: number;
  reward3?: number;
  reward4?: number;
  reward5?: number;
}

export class MQuestClearRewardWeight implements IMQuestClearRewardWeight {
  constructor(
    public id?: number,
    public reward1?: number,
    public reward2?: number,
    public reward3?: number,
    public reward4?: number,
    public reward5?: number
  ) {}
}
