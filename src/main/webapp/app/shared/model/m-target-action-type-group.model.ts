export interface IMTargetActionTypeGroup {
  id?: number;
  groupId?: number;
  commandType?: number;
}

export class MTargetActionTypeGroup implements IMTargetActionTypeGroup {
  constructor(public id?: number, public groupId?: number, public commandType?: number) {}
}
