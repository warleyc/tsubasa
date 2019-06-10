export interface IMAnnounceText {
  id?: number;
  groupId?: number;
  seqNo?: number;
  normalAnnounce?: any;
  criticalSAnnounce?: any;
  criticalMAnnounce?: any;
  criticalLAnnounce?: any;
  delayTime?: number;
  continueTime?: number;
}

export class MAnnounceText implements IMAnnounceText {
  constructor(
    public id?: number,
    public groupId?: number,
    public seqNo?: number,
    public normalAnnounce?: any,
    public criticalSAnnounce?: any,
    public criticalMAnnounce?: any,
    public criticalLAnnounce?: any,
    public delayTime?: number,
    public continueTime?: number
  ) {}
}
