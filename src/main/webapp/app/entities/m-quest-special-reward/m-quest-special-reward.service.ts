import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';

type EntityResponseType = HttpResponse<IMQuestSpecialReward>;
type EntityArrayResponseType = HttpResponse<IMQuestSpecialReward[]>;

@Injectable({ providedIn: 'root' })
export class MQuestSpecialRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-special-rewards';

  constructor(protected http: HttpClient) {}

  create(mQuestSpecialReward: IMQuestSpecialReward): Observable<EntityResponseType> {
    return this.http.post<IMQuestSpecialReward>(this.resourceUrl, mQuestSpecialReward, { observe: 'response' });
  }

  update(mQuestSpecialReward: IMQuestSpecialReward): Observable<EntityResponseType> {
    return this.http.put<IMQuestSpecialReward>(this.resourceUrl, mQuestSpecialReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestSpecialReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestSpecialReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
