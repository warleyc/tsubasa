import { IMDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

export interface IMNpcDeck {
  id?: number;
  teamName?: any;
  uniformBottomFp?: number;
  uniformUpFp?: number;
  uniformBottomGk?: number;
  uniformUpGk?: number;
  formationId?: number;
  captainCardId?: number;
  teamEffect1CardId?: number;
  teamEffect2CardId?: number;
  teamEffect3CardId?: number;
  npcCardId1?: number;
  npcCardId2?: number;
  npcCardId3?: number;
  npcCardId4?: number;
  npcCardId5?: number;
  npcCardId6?: number;
  npcCardId7?: number;
  npcCardId8?: number;
  npcCardId9?: number;
  npcCardId10?: number;
  npcCardId11?: number;
  tick?: number;
  idId?: number;
  mDummyOpponents?: IMDummyOpponent[];
}

export class MNpcDeck implements IMNpcDeck {
  constructor(
    public id?: number,
    public teamName?: any,
    public uniformBottomFp?: number,
    public uniformUpFp?: number,
    public uniformBottomGk?: number,
    public uniformUpGk?: number,
    public formationId?: number,
    public captainCardId?: number,
    public teamEffect1CardId?: number,
    public teamEffect2CardId?: number,
    public teamEffect3CardId?: number,
    public npcCardId1?: number,
    public npcCardId2?: number,
    public npcCardId3?: number,
    public npcCardId4?: number,
    public npcCardId5?: number,
    public npcCardId6?: number,
    public npcCardId7?: number,
    public npcCardId8?: number,
    public npcCardId9?: number,
    public npcCardId10?: number,
    public npcCardId11?: number,
    public tick?: number,
    public idId?: number,
    public mDummyOpponents?: IMDummyOpponent[]
  ) {}
}
