export interface IMQuestDropRateCampaignContent {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  rate?: number;
}

export class MQuestDropRateCampaignContent implements IMQuestDropRateCampaignContent {
  constructor(public id?: number, public groupId?: number, public contentType?: number, public contentId?: number, public rate?: number) {}
}
