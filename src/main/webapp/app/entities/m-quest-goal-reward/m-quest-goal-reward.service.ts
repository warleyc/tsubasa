import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';

type EntityResponseType = HttpResponse<IMQuestGoalReward>;
type EntityArrayResponseType = HttpResponse<IMQuestGoalReward[]>;

@Injectable({ providedIn: 'root' })
export class MQuestGoalRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-goal-rewards';

  constructor(protected http: HttpClient) {}

  create(mQuestGoalReward: IMQuestGoalReward): Observable<EntityResponseType> {
    return this.http.post<IMQuestGoalReward>(this.resourceUrl, mQuestGoalReward, { observe: 'response' });
  }

  update(mQuestGoalReward: IMQuestGoalReward): Observable<EntityResponseType> {
    return this.http.put<IMQuestGoalReward>(this.resourceUrl, mQuestGoalReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestGoalReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestGoalReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
