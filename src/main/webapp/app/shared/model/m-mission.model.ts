import { IMAchievement } from 'app/shared/model/m-achievement.model';

export interface IMMission {
  id?: number;
  term?: number;
  roundNum?: number;
  title?: any;
  description?: any;
  missionType?: number;
  absolute?: number;
  param1?: number;
  param2?: number;
  param3?: number;
  param4?: number;
  param5?: number;
  trigger?: number;
  triggerCondition?: number;
  rewardId?: number;
  startAt?: number;
  endAt?: number;
  link?: number;
  sceneTransitionParam?: any;
  pickup?: number;
  orderNum?: number;
  idId?: number;
  mAchievements?: IMAchievement[];
}

export class MMission implements IMMission {
  constructor(
    public id?: number,
    public term?: number,
    public roundNum?: number,
    public title?: any,
    public description?: any,
    public missionType?: number,
    public absolute?: number,
    public param1?: number,
    public param2?: number,
    public param3?: number,
    public param4?: number,
    public param5?: number,
    public trigger?: number,
    public triggerCondition?: number,
    public rewardId?: number,
    public startAt?: number,
    public endAt?: number,
    public link?: number,
    public sceneTransitionParam?: any,
    public pickup?: number,
    public orderNum?: number,
    public idId?: number,
    public mAchievements?: IMAchievement[]
  ) {}
}
