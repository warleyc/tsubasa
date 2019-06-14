import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMLuckWeeklyQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMLuckWeeklyQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-luck-weekly-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMLuckWeeklyQuestStageReward>(this.resourceUrl, mLuckWeeklyQuestStageReward, { observe: 'response' });
  }

  update(mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMLuckWeeklyQuestStageReward>(this.resourceUrl, mLuckWeeklyQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLuckWeeklyQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLuckWeeklyQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
