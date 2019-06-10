export interface IMDeckCondition {
  id?: number;
  targetFormationGroupId?: number;
  targetCharacterGroupMinId?: number;
  targetCharacterGroupMaxId?: number;
  targetPlayableCardGroupMinId?: number;
  targetPlayableCardGroupMaxId?: number;
  targetRarityGroupId?: number;
  targetAttribute?: number;
  targetNationalityGroupMinId?: number;
  targetNationalityGroupMaxId?: number;
  targetTeamGroupMinId?: number;
  targetTeamGroupMaxId?: number;
  targetActionGroupMinId?: number;
  targetActionGroupMaxId?: number;
  targetTriggerEffectGroupMinId?: number;
  targetTriggerEffectGroupMaxId?: number;
  targetCharacterMinCount?: number;
  targetCharacterMaxCount?: number;
  targetPlayableCardMinCount?: number;
  targetPlayableCardMaxCount?: number;
  targetRarityMaxCount?: number;
  targetAttributeMinCount?: number;
  targetNationalityMinCount?: number;
  targetNationalityMaxCount?: number;
  targetTeamMinCount?: number;
  targetTeamMaxCount?: number;
  targetActionMinCount?: number;
  targetActionMaxCount?: number;
  targetTriggerEffectMinCount?: number;
  targetTriggerEffectMaxCount?: number;
  targetCharacterIsStartingMin?: number;
  targetCharacterIsStartingMax?: number;
  targetPlayableCardIsStartingMin?: number;
  targetPlayableCardIsStartingMax?: number;
  targetRarityIsStarting?: number;
  targetAttributeIsStarting?: number;
  targetNationalityIsStartingMin?: number;
  targetNationalityIsStartingMax?: number;
  targetTeamIsStartingMin?: number;
  targetTeamIsStartingMax?: number;
  targetActionIsStartingMin?: number;
  targetActionIsStartingMax?: number;
  targetTriggerEffectIsStartingMin?: number;
  targetTriggerEffectIsStartingMax?: number;
}

export class MDeckCondition implements IMDeckCondition {
  constructor(
    public id?: number,
    public targetFormationGroupId?: number,
    public targetCharacterGroupMinId?: number,
    public targetCharacterGroupMaxId?: number,
    public targetPlayableCardGroupMinId?: number,
    public targetPlayableCardGroupMaxId?: number,
    public targetRarityGroupId?: number,
    public targetAttribute?: number,
    public targetNationalityGroupMinId?: number,
    public targetNationalityGroupMaxId?: number,
    public targetTeamGroupMinId?: number,
    public targetTeamGroupMaxId?: number,
    public targetActionGroupMinId?: number,
    public targetActionGroupMaxId?: number,
    public targetTriggerEffectGroupMinId?: number,
    public targetTriggerEffectGroupMaxId?: number,
    public targetCharacterMinCount?: number,
    public targetCharacterMaxCount?: number,
    public targetPlayableCardMinCount?: number,
    public targetPlayableCardMaxCount?: number,
    public targetRarityMaxCount?: number,
    public targetAttributeMinCount?: number,
    public targetNationalityMinCount?: number,
    public targetNationalityMaxCount?: number,
    public targetTeamMinCount?: number,
    public targetTeamMaxCount?: number,
    public targetActionMinCount?: number,
    public targetActionMaxCount?: number,
    public targetTriggerEffectMinCount?: number,
    public targetTriggerEffectMaxCount?: number,
    public targetCharacterIsStartingMin?: number,
    public targetCharacterIsStartingMax?: number,
    public targetPlayableCardIsStartingMin?: number,
    public targetPlayableCardIsStartingMax?: number,
    public targetRarityIsStarting?: number,
    public targetAttributeIsStarting?: number,
    public targetNationalityIsStartingMin?: number,
    public targetNationalityIsStartingMax?: number,
    public targetTeamIsStartingMin?: number,
    public targetTeamIsStartingMax?: number,
    public targetActionIsStartingMin?: number,
    public targetActionIsStartingMax?: number,
    public targetTriggerEffectIsStartingMin?: number,
    public targetTriggerEffectIsStartingMax?: number
  ) {}
}
