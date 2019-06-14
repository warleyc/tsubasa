import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMTimeattackQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMTimeattackQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-timeattack-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mTimeattackQuestStageReward: IMTimeattackQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMTimeattackQuestStageReward>(this.resourceUrl, mTimeattackQuestStageReward, { observe: 'response' });
  }

  update(mTimeattackQuestStageReward: IMTimeattackQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMTimeattackQuestStageReward>(this.resourceUrl, mTimeattackQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTimeattackQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTimeattackQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
