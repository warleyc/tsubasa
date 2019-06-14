import { IMLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

export interface IMLuckWeeklyQuestWorld {
  id?: number;
  setId?: number;
  number?: number;
  name?: any;
  imagePath?: any;
  description?: any;
  stageUnlockPattern?: number;
  arousalBanner?: any;
  specialRewardContentType?: number;
  specialRewardContentId?: number;
  isEnableCoop?: number;
  clearLimit?: number;
  mLuckWeeklyQuestStages?: IMLuckWeeklyQuestStage[];
}

export class MLuckWeeklyQuestWorld implements IMLuckWeeklyQuestWorld {
  constructor(
    public id?: number,
    public setId?: number,
    public number?: number,
    public name?: any,
    public imagePath?: any,
    public description?: any,
    public stageUnlockPattern?: number,
    public arousalBanner?: any,
    public specialRewardContentType?: number,
    public specialRewardContentId?: number,
    public isEnableCoop?: number,
    public clearLimit?: number,
    public mLuckWeeklyQuestStages?: IMLuckWeeklyQuestStage[]
  ) {}
}
