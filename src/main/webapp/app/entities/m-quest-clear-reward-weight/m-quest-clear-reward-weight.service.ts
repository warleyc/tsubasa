import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';

type EntityResponseType = HttpResponse<IMQuestClearRewardWeight>;
type EntityArrayResponseType = HttpResponse<IMQuestClearRewardWeight[]>;

@Injectable({ providedIn: 'root' })
export class MQuestClearRewardWeightService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-clear-reward-weights';

  constructor(protected http: HttpClient) {}

  create(mQuestClearRewardWeight: IMQuestClearRewardWeight): Observable<EntityResponseType> {
    return this.http.post<IMQuestClearRewardWeight>(this.resourceUrl, mQuestClearRewardWeight, { observe: 'response' });
  }

  update(mQuestClearRewardWeight: IMQuestClearRewardWeight): Observable<EntityResponseType> {
    return this.http.put<IMQuestClearRewardWeight>(this.resourceUrl, mQuestClearRewardWeight, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestClearRewardWeight>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestClearRewardWeight[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
