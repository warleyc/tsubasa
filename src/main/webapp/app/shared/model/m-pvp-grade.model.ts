export interface IMPvpGrade {
  id?: number;
  waveId?: number;
  grade?: number;
  isHigher?: number;
  isLower?: number;
  gradeName?: any;
  look?: number;
  upRequirementId?: number;
  downRequirementId?: number;
  winPoint?: number;
  losePoint?: number;
  gradeUpSerif?: any;
  gradeDownSerif?: any;
  gradeUpCharaAssetName?: any;
  gradeDownCharaAssetName?: any;
  gradeUpVoiceCharaId?: any;
  gradeDownVoiceCharaId?: any;
  totalParameterRangeUpper?: number;
  totalParameterRangeLower?: number;
}

export class MPvpGrade implements IMPvpGrade {
  constructor(
    public id?: number,
    public waveId?: number,
    public grade?: number,
    public isHigher?: number,
    public isLower?: number,
    public gradeName?: any,
    public look?: number,
    public upRequirementId?: number,
    public downRequirementId?: number,
    public winPoint?: number,
    public losePoint?: number,
    public gradeUpSerif?: any,
    public gradeDownSerif?: any,
    public gradeUpCharaAssetName?: any,
    public gradeDownCharaAssetName?: any,
    public gradeUpVoiceCharaId?: any,
    public gradeDownVoiceCharaId?: any,
    public totalParameterRangeUpper?: number,
    public totalParameterRangeLower?: number
  ) {}
}
