export interface IMQuestStageConditionDescription {
  id?: number;
  successConditionType?: number;
  successConditionDetailTypeValue?: number;
  hasExistTargetCharacterGroup?: number;
  hasSuccessConditionValueOneOnly?: number;
  failureConditionTypeValue?: number;
  description?: any;
}

export class MQuestStageConditionDescription implements IMQuestStageConditionDescription {
  constructor(
    public id?: number,
    public successConditionType?: number,
    public successConditionDetailTypeValue?: number,
    public hasExistTargetCharacterGroup?: number,
    public hasSuccessConditionValueOneOnly?: number,
    public failureConditionTypeValue?: number,
    public description?: any
  ) {}
}
