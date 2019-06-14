export interface IMMasterVersion {
  id?: number;
  name?: any;
  version?: number;
  path?: any;
  size?: number;
}

export class MMasterVersion implements IMMasterVersion {
  constructor(public id?: number, public name?: any, public version?: number, public path?: any, public size?: number) {}
}
