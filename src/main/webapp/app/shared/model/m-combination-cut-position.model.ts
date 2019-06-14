export interface IMCombinationCutPosition {
  id?: number;
  actionCutId?: number;
  characterId?: number;
  activatorPosition?: number;
  participantPosition1?: number;
  participantPosition2?: number;
  participantPosition3?: number;
  participantPosition4?: number;
  participantPosition5?: number;
  mcharacterId?: number;
}

export class MCombinationCutPosition implements IMCombinationCutPosition {
  constructor(
    public id?: number,
    public actionCutId?: number,
    public characterId?: number,
    public activatorPosition?: number,
    public participantPosition1?: number,
    public participantPosition2?: number,
    public participantPosition3?: number,
    public participantPosition4?: number,
    public participantPosition5?: number,
    public mcharacterId?: number
  ) {}
}
