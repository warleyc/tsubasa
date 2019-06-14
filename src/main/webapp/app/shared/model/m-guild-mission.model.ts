export interface IMGuildMission {
  id?: number;
  term?: number;
  title?: any;
  description?: any;
  missionType?: number;
  param1?: number;
  rewardId?: number;
  link?: number;
  sceneTransitionParam?: any;
  pickup?: number;
  triggerMissionId?: number;
  orderNum?: number;
  mmissionrewardId?: number;
}

export class MGuildMission implements IMGuildMission {
  constructor(
    public id?: number,
    public term?: number,
    public title?: any,
    public description?: any,
    public missionType?: number,
    public param1?: number,
    public rewardId?: number,
    public link?: number,
    public sceneTransitionParam?: any,
    public pickup?: number,
    public triggerMissionId?: number,
    public orderNum?: number,
    public mmissionrewardId?: number
  ) {}
}
