import { IMChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

export interface IMChallengeQuestWorld {
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
  mChallengeQuestStages?: IMChallengeQuestStage[];
}

export class MChallengeQuestWorld implements IMChallengeQuestWorld {
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
    public mChallengeQuestStages?: IMChallengeQuestStage[]
  ) {}
}
