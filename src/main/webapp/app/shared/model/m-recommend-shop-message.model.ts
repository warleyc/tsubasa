export interface IMRecommendShopMessage {
  id?: number;
  message?: any;
  hasSalesPeriod?: number;
}

export class MRecommendShopMessage implements IMRecommendShopMessage {
  constructor(public id?: number, public message?: any, public hasSalesPeriod?: number) {}
}
