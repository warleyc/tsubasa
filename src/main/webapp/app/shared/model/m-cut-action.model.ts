export interface IMCutAction {
  id?: number;
  actionCutId?: number;
  cutActionType?: number;
  cutSequenceText?: any;
}

export class MCutAction implements IMCutAction {
  constructor(public id?: number, public actionCutId?: number, public cutActionType?: number, public cutSequenceText?: any) {}
}
