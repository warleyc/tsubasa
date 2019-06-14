export interface IMTargetCharacterGroup {
  id?: number;
  groupId?: number;
  characterId?: number;
  mcharacterId?: number;
}

export class MTargetCharacterGroup implements IMTargetCharacterGroup {
  constructor(public id?: number, public groupId?: number, public characterId?: number, public mcharacterId?: number) {}
}
