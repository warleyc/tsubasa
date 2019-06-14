import { IMTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

export interface IMTicketQuestWorld {
  id?: number;
  setId?: number;
  number?: number;
  name?: any;
  imagePath?: any;
  description?: any;
  stageUnlockPattern?: number;
  specialRewardContentType?: number;
  specialRewardContentId?: number;
  isEnableCoop?: number;
  isHideDoNotHavingTicket?: number;
  mTicketQuestStages?: IMTicketQuestStage[];
}

export class MTicketQuestWorld implements IMTicketQuestWorld {
  constructor(
    public id?: number,
    public setId?: number,
    public number?: number,
    public name?: any,
    public imagePath?: any,
    public description?: any,
    public stageUnlockPattern?: number,
    public specialRewardContentType?: number,
    public specialRewardContentId?: number,
    public isEnableCoop?: number,
    public isHideDoNotHavingTicket?: number,
    public mTicketQuestStages?: IMTicketQuestStage[]
  ) {}
}
