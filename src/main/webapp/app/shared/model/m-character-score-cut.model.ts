export interface IMCharacterScoreCut {
  id?: number;
  characterId?: number;
  teamId?: number;
  scoreCutType?: number;
}

export class MCharacterScoreCut implements IMCharacterScoreCut {
  constructor(public id?: number, public characterId?: number, public teamId?: number, public scoreCutType?: number) {}
}
