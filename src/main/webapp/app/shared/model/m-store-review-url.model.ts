export interface IMStoreReviewUrl {
  id?: number;
  platform?: number;
  url?: any;
}

export class MStoreReviewUrl implements IMStoreReviewUrl {
  constructor(public id?: number, public platform?: number, public url?: any) {}
}
