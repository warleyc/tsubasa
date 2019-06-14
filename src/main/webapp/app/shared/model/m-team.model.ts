import { IMTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

export interface IMTeam {
  id?: number;
  name?: any;
  mTargetTeamGroups?: IMTargetTeamGroup[];
}

export class MTeam implements IMTeam {
  constructor(public id?: number, public name?: any, public mTargetTeamGroups?: IMTargetTeamGroup[]) {}
}
