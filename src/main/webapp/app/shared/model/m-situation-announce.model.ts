export interface IMSituationAnnounce {
  id?: number;
  situationId?: number;
  groupId?: number;
}

export class MSituationAnnounce implements IMSituationAnnounce {
  constructor(public id?: number, public situationId?: number, public groupId?: number) {}
}
