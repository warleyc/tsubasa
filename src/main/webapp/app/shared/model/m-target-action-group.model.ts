export interface IMTargetActionGroup {
  id?: number;
  groupId?: number;
  actionId?: number;
  mactionId?: number;
}

export class MTargetActionGroup implements IMTargetActionGroup {
  constructor(public id?: number, public groupId?: number, public actionId?: number, public mactionId?: number) {}
}
