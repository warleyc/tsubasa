export interface IMTimeattackQuestStage {
  id?: number;
  worldId?: number;
  number?: number;
  startAt?: number;
  name?: any;
  kickoffDescription?: any;
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
  ticketId?: number;
  ticketAmount?: number;
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
  mtimeattackquestworldId?: number;
}

export class MTimeattackQuestStage implements IMTimeattackQuestStage {
  constructor(
    public id?: number,
    public worldId?: number,
    public number?: number,
    public startAt?: number,
    public name?: any,
    public kickoffDescription?: any,
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
    public ticketId?: number,
    public ticketAmount?: number,
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
    public mtimeattackquestworldId?: number
  ) {}
}
