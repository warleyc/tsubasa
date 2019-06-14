export interface IMShoot {
  id?: number;
  angleDecayType?: number;
  shootOrbit?: number;
  judgementId?: number;
}

export class MShoot implements IMShoot {
  constructor(public id?: number, public angleDecayType?: number, public shootOrbit?: number, public judgementId?: number) {}
}
