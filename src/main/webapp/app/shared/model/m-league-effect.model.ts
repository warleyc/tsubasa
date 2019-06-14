export interface IMLeagueEffect {
  id?: number;
  effectType?: number;
  leagueHierarchy?: number;
  rate?: number;
  price?: number;
}

export class MLeagueEffect implements IMLeagueEffect {
  constructor(
    public id?: number,
    public effectType?: number,
    public leagueHierarchy?: number,
    public rate?: number,
    public price?: number
  ) {}
}
