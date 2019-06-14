export interface IMSoundBank {
  id?: number;
  path?: any;
  pf?: any;
  version?: number;
  fileSize?: number;
}

export class MSoundBank implements IMSoundBank {
  constructor(public id?: number, public path?: any, public pf?: any, public version?: number, public fileSize?: number) {}
}
