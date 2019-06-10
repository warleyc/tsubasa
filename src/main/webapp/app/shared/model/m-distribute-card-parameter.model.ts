export interface IMDistributeCardParameter {
  id?: number;
  rarity?: number;
  isGk?: number;
  maxStepCount?: number;
  maxTotalStepCount?: number;
  distributePointByStep?: number;
}

export class MDistributeCardParameter implements IMDistributeCardParameter {
  constructor(
    public id?: number,
    public rarity?: number,
    public isGk?: number,
    public maxStepCount?: number,
    public maxTotalStepCount?: number,
    public distributePointByStep?: number
  ) {}
}
