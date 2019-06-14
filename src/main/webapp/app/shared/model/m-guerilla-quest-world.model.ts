import { IMGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

export interface IMGuerillaQuestWorld {
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
  mGuerillaQuestStages?: IMGuerillaQuestStage[];
}

export class MGuerillaQuestWorld implements IMGuerillaQuestWorld {
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
    public mGuerillaQuestStages?: IMGuerillaQuestStage[]
  ) {}
}
