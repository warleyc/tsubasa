import { IMLeagueRegulation } from 'app/shared/model/m-league-regulation.model';
import { IMPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';

export interface IMMatchOption {
  id?: number;
  passiveEffectId?: number;
  passiveEffectValue?: number;
  activateFullPowerType?: number;
  attributeMagnification?: number;
  useStaminaMagnification?: number;
  isIgnoreTeamSkill?: number;
  staminaInfinityType?: number;
  mpassiveeffectrangeId?: number;
  mLeagueRegulations?: IMLeagueRegulation[];
  mPvpRegulations?: IMPvpRegulation[];
}

export class MMatchOption implements IMMatchOption {
  constructor(
    public id?: number,
    public passiveEffectId?: number,
    public passiveEffectValue?: number,
    public activateFullPowerType?: number,
    public attributeMagnification?: number,
    public useStaminaMagnification?: number,
    public isIgnoreTeamSkill?: number,
    public staminaInfinityType?: number,
    public mpassiveeffectrangeId?: number,
    public mLeagueRegulations?: IMLeagueRegulation[],
    public mPvpRegulations?: IMPvpRegulation[]
  ) {}
}
