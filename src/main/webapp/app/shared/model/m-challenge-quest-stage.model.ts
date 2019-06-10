export interface IMChallengeQuestStage {
  id?: number;
  worldId?: number;
  number?: number;
  name?: any;
  imagePath?: any;
  characterThumbnailPath?: any;
  difficulty?: number;
  stageUnlockPattern?: number;
  storyOnly?: number;
  firstHalfNpcDeckId?: number;
  firstHalfEnvironmentId?: number;
  secondHalfNpcDeckId?: number;
  secondHalfEnvironmentId?: number;
  extraFirstHalfNpcDeckId?: number;
  consumeAp?: number;
  kickOffType?: number;
  matchMinute?: number;
  enableExtra?: number;
  enablePk?: number;
  aiLevel?: number;
  startAtSecondHalf?: number;
  startScore?: number;
  startScoreOpponent?: number;
  conditionId?: number;
  optionId?: number;
  deckConditionId?: number;
  shortName?: any;
  skipCheckPoint?: number;
  roadNumber?: number;
  idId?: number;
}

export class MChallengeQuestStage implements IMChallengeQuestStage {
  constructor(
    public id?: number,
    public worldId?: number,
    public number?: number,
    public name?: any,
    public imagePath?: any,
    public characterThumbnailPath?: any,
    public difficulty?: number,
    public stageUnlockPattern?: number,
    public storyOnly?: number,
    public firstHalfNpcDeckId?: number,
    public firstHalfEnvironmentId?: number,
    public secondHalfNpcDeckId?: number,
    public secondHalfEnvironmentId?: number,
    public extraFirstHalfNpcDeckId?: number,
    public consumeAp?: number,
    public kickOffType?: number,
    public matchMinute?: number,
    public enableExtra?: number,
    public enablePk?: number,
    public aiLevel?: number,
    public startAtSecondHalf?: number,
    public startScore?: number,
    public startScoreOpponent?: number,
    public conditionId?: number,
    public optionId?: number,
    public deckConditionId?: number,
    public shortName?: any,
    public skipCheckPoint?: number,
    public roadNumber?: number,
    public idId?: number
  ) {}
}
