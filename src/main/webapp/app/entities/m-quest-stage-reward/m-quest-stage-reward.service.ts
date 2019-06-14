import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mQuestStageReward: IMQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMQuestStageReward>(this.resourceUrl, mQuestStageReward, { observe: 'response' });
  }

  update(mQuestStageReward: IMQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMQuestStageReward>(this.resourceUrl, mQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
