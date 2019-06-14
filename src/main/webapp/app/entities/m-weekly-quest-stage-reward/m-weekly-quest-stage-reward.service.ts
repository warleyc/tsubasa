import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMWeeklyQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMWeeklyQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-weekly-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mWeeklyQuestStageReward: IMWeeklyQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMWeeklyQuestStageReward>(this.resourceUrl, mWeeklyQuestStageReward, { observe: 'response' });
  }

  update(mWeeklyQuestStageReward: IMWeeklyQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMWeeklyQuestStageReward>(this.resourceUrl, mWeeklyQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMWeeklyQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMWeeklyQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
