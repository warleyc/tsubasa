export interface IMLeague {
  id?: number;
  hierarchy?: number;
  weight?: number;
  name?: any;
  rooms?: number;
  promotionRate?: number;
  demotionRate?: number;
  totalParameterRangeUpper?: number;
  totalParameterRangeLower?: number;
  requiredMatches?: number;
  startWeekId?: number;
}

export class MLeague implements IMLeague {
  constructor(
    public id?: number,
    public hierarchy?: number,
    public weight?: number,
    public name?: any,
    public rooms?: number,
    public promotionRate?: number,
    public demotionRate?: number,
    public totalParameterRangeUpper?: number,
    public totalParameterRangeLower?: number,
    public requiredMatches?: number,
    public startWeekId?: number
  ) {}
}
