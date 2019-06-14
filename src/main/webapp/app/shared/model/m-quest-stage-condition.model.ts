export interface IMQuestStageCondition {
  id?: number;
  successConditionType?: number;
  successConditionDetailType?: number;
  successConditionValue?: number;
  targetCharacterGroupId?: number;
  failureConditionType?: number;
}

export class MQuestStageCondition implements IMQuestStageCondition {
  constructor(
    public id?: number,
    public successConditionType?: number,
    public successConditionDetailType?: number,
    public successConditionValue?: number,
    public targetCharacterGroupId?: number,
    public failureConditionType?: number
  ) {}
}
