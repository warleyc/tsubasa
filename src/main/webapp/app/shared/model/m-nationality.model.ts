import { IMTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

export interface IMNationality {
  id?: number;
  name?: any;
  icon?: any;
  mTargetNationalityGroups?: IMTargetNationalityGroup[];
}

export class MNationality implements IMNationality {
  constructor(public id?: number, public name?: any, public icon?: any, public mTargetNationalityGroups?: IMTargetNationalityGroup[]) {}
}
