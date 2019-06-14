export interface IMRecommendFormationFilterElement {
  id?: number;
  filterType?: number;
  elementId?: number;
  name?: any;
}

export class MRecommendFormationFilterElement implements IMRecommendFormationFilterElement {
  constructor(public id?: number, public filterType?: number, public elementId?: number, public name?: any) {}
}
