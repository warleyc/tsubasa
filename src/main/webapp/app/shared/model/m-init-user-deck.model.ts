export interface IMInitUserDeck {
  id?: number;
  deckId?: number;
  name?: any;
  formationId?: number;
  captainCardId?: number;
  gkCardId?: number;
  fp1CardId?: number;
  fp2CardId?: number;
  fp3CardId?: number;
  fp4CardId?: number;
  fp5CardId?: number;
  fp6CardId?: number;
  fp7CardId?: number;
  fp8CardId?: number;
  fp9CardId?: number;
  fp10CardId?: number;
  sub1CardId?: number;
  sub2CardId?: number;
  sub3CardId?: number;
  sub4CardId?: number;
  sub5CardId?: number;
  teamEffect1CardId?: number;
  teamEffect2CardId?: number;
  teamEffect3CardId?: number;
}

export class MInitUserDeck implements IMInitUserDeck {
  constructor(
    public id?: number,
    public deckId?: number,
    public name?: any,
    public formationId?: number,
    public captainCardId?: number,
    public gkCardId?: number,
    public fp1CardId?: number,
    public fp2CardId?: number,
    public fp3CardId?: number,
    public fp4CardId?: number,
    public fp5CardId?: number,
    public fp6CardId?: number,
    public fp7CardId?: number,
    public fp8CardId?: number,
    public fp9CardId?: number,
    public fp10CardId?: number,
    public sub1CardId?: number,
    public sub2CardId?: number,
    public sub3CardId?: number,
    public sub4CardId?: number,
    public sub5CardId?: number,
    public teamEffect1CardId?: number,
    public teamEffect2CardId?: number,
    public teamEffect3CardId?: number
  ) {}
}
