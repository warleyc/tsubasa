export interface IMPvpGradeRequirement {
  id?: number;
  upDescription?: any;
  downDescription?: any;
  targetType?: number;
  targetWave?: number;
  targetLowerGrade?: number;
  targetPoint?: number;
}

export class MPvpGradeRequirement implements IMPvpGradeRequirement {
  constructor(
    public id?: number,
    public upDescription?: any,
    public downDescription?: any,
    public targetType?: number,
    public targetWave?: number,
    public targetLowerGrade?: number,
    public targetPoint?: number
  ) {}
}
