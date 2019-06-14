export interface IMTargetTeamGroup {
  id?: number;
  groupId?: number;
  teamId?: number;
  mteamId?: number;
}

export class MTargetTeamGroup implements IMTargetTeamGroup {
  constructor(public id?: number, public groupId?: number, public teamId?: number, public mteamId?: number) {}
}
