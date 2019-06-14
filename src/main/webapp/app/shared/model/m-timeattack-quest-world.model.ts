import { IMTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';

export interface IMTimeattackQuestWorld {
  id?: number;
  setId?: number;
  number?: number;
  name?: any;
  imagePath?: any;
  backgroundImagePath?: any;
  description?: any;
  stageUnlockPattern?: number;
  arousalBanner?: any;
  specialRewardContentType?: number;
  specialRewardContentId?: number;
  isEnableCoop?: number;
  mTimeattackQuestStages?: IMTimeattackQuestStage[];
}

export class MTimeattackQuestWorld implements IMTimeattackQuestWorld {
  constructor(
    public id?: number,
    public setId?: number,
    public number?: number,
    public name?: any,
    public imagePath?: any,
    public backgroundImagePath?: any,
    public description?: any,
    public stageUnlockPattern?: number,
    public arousalBanner?: any,
    public specialRewardContentType?: number,
    public specialRewardContentId?: number,
    public isEnableCoop?: number,
    public mTimeattackQuestStages?: IMTimeattackQuestStage[]
  ) {}
}
