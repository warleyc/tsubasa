import { IMQuestStage } from 'app/shared/model/m-quest-stage.model';

export interface IMQuestWorld {
  id?: number;
  number?: number;
  name?: any;
  startAt?: number;
  imagePath?: any;
  backgroundImagePath?: any;
  description?: any;
  stageUnlockPattern?: number;
  specialRewardContentType?: number;
  specialRewardContentId?: number;
  isEnableCoop?: number;
  mQuestStages?: IMQuestStage[];
}

export class MQuestWorld implements IMQuestWorld {
  constructor(
    public id?: number,
    public number?: number,
    public name?: any,
    public startAt?: number,
    public imagePath?: any,
    public backgroundImagePath?: any,
    public description?: any,
    public stageUnlockPattern?: number,
    public specialRewardContentType?: number,
    public specialRewardContentId?: number,
    public isEnableCoop?: number,
    public mQuestStages?: IMQuestStage[]
  ) {}
}
