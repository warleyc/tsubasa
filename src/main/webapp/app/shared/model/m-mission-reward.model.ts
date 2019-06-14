import { IMGuildMission } from 'app/shared/model/m-guild-mission.model';
import { IMMission } from 'app/shared/model/m-mission.model';

export interface IMMissionReward {
  id?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
  mGuildMissions?: IMGuildMission[];
  mMissions?: IMMission[];
}

export class MMissionReward implements IMMissionReward {
  constructor(
    public id?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number,
    public mGuildMissions?: IMGuildMission[],
    public mMissions?: IMMission[]
  ) {}
}
