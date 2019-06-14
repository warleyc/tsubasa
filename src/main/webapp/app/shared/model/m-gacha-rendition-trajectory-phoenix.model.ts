export interface IMGachaRenditionTrajectoryPhoenix {
  id?: number;
  isPhoenix?: number;
  weight?: number;
}

export class MGachaRenditionTrajectoryPhoenix implements IMGachaRenditionTrajectoryPhoenix {
  constructor(public id?: number, public isPhoenix?: number, public weight?: number) {}
}
