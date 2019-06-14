export interface IMQuestCoop {
  id?: number;
  groupId?: number;
  effectRarity?: number;
  effectLevelFrom?: number;
  effectLevelTo?: number;
  choose1Weight?: number;
  choose2Weight?: number;
}

export class MQuestCoop implements IMQuestCoop {
  constructor(
    public id?: number,
    public groupId?: number,
    public effectRarity?: number,
    public effectLevelFrom?: number,
    public effectLevelTo?: number,
    public choose1Weight?: number,
    public choose2Weight?: number
  ) {}
}
