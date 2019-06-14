export interface IMAchievement {
  id?: number;
  achievementId?: any;
  name?: any;
  type?: number;
  missionId?: number;
  mmissionId?: number;
}

export class MAchievement implements IMAchievement {
  constructor(
    public id?: number,
    public achievementId?: any,
    public name?: any,
    public type?: number,
    public missionId?: number,
    public mmissionId?: number
  ) {}
}
