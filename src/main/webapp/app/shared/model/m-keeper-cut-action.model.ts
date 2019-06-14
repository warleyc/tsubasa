export interface IMKeeperCutAction {
  id?: number;
  actionCutId?: number;
  keeperCutActionType?: number;
  cutSequenceText?: any;
}

export class MKeeperCutAction implements IMKeeperCutAction {
  constructor(public id?: number, public actionCutId?: number, public keeperCutActionType?: number, public cutSequenceText?: any) {}
}
