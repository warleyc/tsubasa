export interface IMRecommendFormationFilter {
  id?: number;
  filterType?: number;
  name?: any;
}

export class MRecommendFormationFilter implements IMRecommendFormationFilter {
  constructor(public id?: number, public filterType?: number, public name?: any) {}
}
