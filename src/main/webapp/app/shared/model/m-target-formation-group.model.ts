export interface IMTargetFormationGroup {
  id?: number;
  groupId?: number;
  formationId?: number;
  mformationId?: number;
}

export class MTargetFormationGroup implements IMTargetFormationGroup {
  constructor(public id?: number, public groupId?: number, public formationId?: number, public mformationId?: number) {}
}
