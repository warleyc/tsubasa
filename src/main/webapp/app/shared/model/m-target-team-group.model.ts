export interface IMTargetTeamGroup {
  id?: number;
  groupId?: number;
  teamId?: number;
  idId?: number;
}

export class MTargetTeamGroup implements IMTargetTeamGroup {
  constructor(public id?: number, public groupId?: number, public teamId?: number, public idId?: number) {}
}
