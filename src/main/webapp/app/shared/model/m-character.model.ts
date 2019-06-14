import { IMActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';
import { IMCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';
import { IMMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';
import { IMNpcCard } from 'app/shared/model/m-npc-card.model';
import { IMTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

export interface IMCharacter {
  id?: number;
  name?: any;
  announceName?: any;
  shortName?: any;
  characterBookPriority?: number;
  mActionSkillHolderCardContents?: IMActionSkillHolderCardContent[];
  mCombinationCutPositions?: IMCombinationCutPosition[];
  mMatchResultCutins?: IMMatchResultCutin[];
  mNpcCards?: IMNpcCard[];
  mTargetCharacterGroups?: IMTargetCharacterGroup[];
}

export class MCharacter implements IMCharacter {
  constructor(
    public id?: number,
    public name?: any,
    public announceName?: any,
    public shortName?: any,
    public characterBookPriority?: number,
    public mActionSkillHolderCardContents?: IMActionSkillHolderCardContent[],
    public mCombinationCutPositions?: IMCombinationCutPosition[],
    public mMatchResultCutins?: IMMatchResultCutin[],
    public mNpcCards?: IMNpcCard[],
    public mTargetCharacterGroups?: IMTargetCharacterGroup[]
  ) {}
}
