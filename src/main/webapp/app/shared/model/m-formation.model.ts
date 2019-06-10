import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';
import { IMTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

export interface IMFormation {
  id?: number;
  effectValue?: number;
  effectProbability?: number;
  formationArrangementFw?: any;
  formationArrangementOmf?: any;
  formationArrangementDmf?: any;
  formationArrangementDf?: any;
  effectId?: number;
  description?: any;
  shortDescription?: any;
  name?: any;
  thumbnailAssetName?: any;
  startingUniformNos?: any;
  subUniformNos?: any;
  exType?: number;
  matchFormationId?: number;
  idId?: number;
  mNpcDecks?: IMNpcDeck[];
  mTargetFormationGroups?: IMTargetFormationGroup[];
}

export class MFormation implements IMFormation {
  constructor(
    public id?: number,
    public effectValue?: number,
    public effectProbability?: number,
    public formationArrangementFw?: any,
    public formationArrangementOmf?: any,
    public formationArrangementDmf?: any,
    public formationArrangementDf?: any,
    public effectId?: number,
    public description?: any,
    public shortDescription?: any,
    public name?: any,
    public thumbnailAssetName?: any,
    public startingUniformNos?: any,
    public subUniformNos?: any,
    public exType?: number,
    public matchFormationId?: number,
    public idId?: number,
    public mNpcDecks?: IMNpcDeck[],
    public mTargetFormationGroups?: IMTargetFormationGroup[]
  ) {}
}
