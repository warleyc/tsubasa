import { IMAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

export interface IMAdventQuestWorld {
  id?: number;
  setId?: number;
  number?: number;
  name?: any;
  symbolType?: number;
  imagePath?: any;
  description?: any;
  stageUnlockPattern?: number;
  arousalBanner?: any;
  specialRewardContentType?: number;
  specialRewardContentId?: number;
  isEnableCoop?: number;
  mAdventQuestStages?: IMAdventQuestStage[];
}

export class MAdventQuestWorld implements IMAdventQuestWorld {
  constructor(
    public id?: number,
    public setId?: number,
    public number?: number,
    public name?: any,
    public symbolType?: number,
    public imagePath?: any,
    public description?: any,
    public stageUnlockPattern?: number,
    public arousalBanner?: any,
    public specialRewardContentType?: number,
    public specialRewardContentId?: number,
    public isEnableCoop?: number,
    public mAdventQuestStages?: IMAdventQuestStage[]
  ) {}
}
