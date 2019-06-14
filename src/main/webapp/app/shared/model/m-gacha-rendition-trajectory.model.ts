export interface IMGachaRenditionTrajectory {
  id?: number;
  weight?: number;
  trajectoryType?: number;
}

export class MGachaRenditionTrajectory implements IMGachaRenditionTrajectory {
  constructor(public id?: number, public weight?: number, public trajectoryType?: number) {}
}
