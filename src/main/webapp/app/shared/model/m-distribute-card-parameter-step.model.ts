export interface IMDistributeCardParameterStep {
  id?: number;
  isGk?: number;
  step?: number;
  param?: number;
}

export class MDistributeCardParameterStep implements IMDistributeCardParameterStep {
  constructor(public id?: number, public isGk?: number, public step?: number, public param?: number) {}
}
