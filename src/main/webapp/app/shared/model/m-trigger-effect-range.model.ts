import { IMTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

export interface IMTriggerEffectRange {
  id?: number;
  effectType?: number;
  targetPositionGk?: number;
  targetPositionFw?: number;
  targetPositionOmf?: number;
  targetPositionDmf?: number;
  targetPositionDf?: number;
  targetAttribute?: number;
  targetNationalityGroupId?: number;
  targetTeamGroupId?: number;
  targetCharacterGroupId?: number;
  targetFormationGroupId?: number;
  targetActionCommand?: number;
  mTriggerEffectBases?: IMTriggerEffectBase[];
}

export class MTriggerEffectRange implements IMTriggerEffectRange {
  constructor(
    public id?: number,
    public effectType?: number,
    public targetPositionGk?: number,
    public targetPositionFw?: number,
    public targetPositionOmf?: number,
    public targetPositionDmf?: number,
    public targetPositionDf?: number,
    public targetAttribute?: number,
    public targetNationalityGroupId?: number,
    public targetTeamGroupId?: number,
    public targetCharacterGroupId?: number,
    public targetFormationGroupId?: number,
    public targetActionCommand?: number,
    public mTriggerEffectBases?: IMTriggerEffectBase[]
  ) {}
}
