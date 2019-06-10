export interface IMDbMapping {
  id?: number;
  fileName?: any;
  dbName?: any;
  path?: any;
}

export class MDbMapping implements IMDbMapping {
  constructor(public id?: number, public fileName?: any, public dbName?: any, public path?: any) {}
}
