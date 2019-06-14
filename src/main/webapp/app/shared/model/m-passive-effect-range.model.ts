import { IMFormation } from 'app/shared/model/m-formation.model';
import { IMMatchOption } from 'app/shared/model/m-match-option.model';
import { IMTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

export interface IMPassiveEffectRange {
  id?: number;
  name?: any;
  effectParamType?: number;
  targetPositionGk?: number;
  targetPositionFw?: number;
  targetPositionOmf?: number;
  targetPositionDmf?: number;
  targetPositionDf?: number;
  targetAttribute?: number;
  targetNationalityGroupId?: number;
  targetTeamGroupId?: number;
  targetPlayableCardGroupId?: number;
  description?: any;
  mFormations?: IMFormation[];
  mMatchOptions?: IMMatchOption[];
  mTeamEffectBases?: IMTeamEffectBase[];
}

export class MPassiveEffectRange implements IMPassiveEffectRange {
  constructor(
    public id?: number,
    public name?: any,
    public effectParamType?: number,
    public targetPositionGk?: number,
    public targetPositionFw?: number,
    public targetPositionOmf?: number,
    public targetPositionDmf?: number,
    public targetPositionDf?: number,
    public targetAttribute?: number,
    public targetNationalityGroupId?: number,
    public targetTeamGroupId?: number,
    public targetPlayableCardGroupId?: number,
    public description?: any,
    public mFormations?: IMFormation[],
    public mMatchOptions?: IMMatchOption[],
    public mTeamEffectBases?: IMTeamEffectBase[]
  ) {}
}
