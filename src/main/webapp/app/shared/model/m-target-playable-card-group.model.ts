export interface IMTargetPlayableCardGroup {
  id?: number;
  groupId?: number;
  cardId?: number;
  isShowThumbnail?: number;
  idId?: number;
}

export class MTargetPlayableCardGroup implements IMTargetPlayableCardGroup {
  constructor(public id?: number, public groupId?: number, public cardId?: number, public isShowThumbnail?: number, public idId?: number) {}
}
