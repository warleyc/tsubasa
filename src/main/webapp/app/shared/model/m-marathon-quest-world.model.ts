import { IMMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

export interface IMMarathonQuestWorld {
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
  mMarathonQuestStages?: IMMarathonQuestStage[];
}

export class MMarathonQuestWorld implements IMMarathonQuestWorld {
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
    public mMarathonQuestStages?: IMMarathonQuestStage[]
  ) {}
}
