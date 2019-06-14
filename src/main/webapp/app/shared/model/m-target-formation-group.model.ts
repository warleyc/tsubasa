export interface IMTargetFormationGroup {
  id?: number;
  groupId?: number;
  formationId?: number;
  idId?: number;
}

export class MTargetFormationGroup implements IMTargetFormationGroup {
  constructor(public id?: number, public groupId?: number, public formationId?: number, public idId?: number) {}
}
