export interface IMTargetPlayableCardGroup {
  id?: number;
  groupId?: number;
  cardId?: number;
  isShowThumbnail?: number;
  mplayablecardId?: number;
}

export class MTargetPlayableCardGroup implements IMTargetPlayableCardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public cardId?: number,
    public isShowThumbnail?: number,
    public mplayablecardId?: number
  ) {}
}
