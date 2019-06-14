import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';

type EntityResponseType = HttpResponse<IMQuestCoopReward>;
type EntityArrayResponseType = HttpResponse<IMQuestCoopReward[]>;

@Injectable({ providedIn: 'root' })
export class MQuestCoopRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-coop-rewards';

  constructor(protected http: HttpClient) {}

  create(mQuestCoopReward: IMQuestCoopReward): Observable<EntityResponseType> {
    return this.http.post<IMQuestCoopReward>(this.resourceUrl, mQuestCoopReward, { observe: 'response' });
  }

  update(mQuestCoopReward: IMQuestCoopReward): Observable<EntityResponseType> {
    return this.http.put<IMQuestCoopReward>(this.resourceUrl, mQuestCoopReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestCoopReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestCoopReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
