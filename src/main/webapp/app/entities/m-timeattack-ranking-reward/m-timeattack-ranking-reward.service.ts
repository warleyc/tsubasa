import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';

type EntityResponseType = HttpResponse<IMTimeattackRankingReward>;
type EntityArrayResponseType = HttpResponse<IMTimeattackRankingReward[]>;

@Injectable({ providedIn: 'root' })
export class MTimeattackRankingRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-timeattack-ranking-rewards';

  constructor(protected http: HttpClient) {}

  create(mTimeattackRankingReward: IMTimeattackRankingReward): Observable<EntityResponseType> {
    return this.http.post<IMTimeattackRankingReward>(this.resourceUrl, mTimeattackRankingReward, { observe: 'response' });
  }

  update(mTimeattackRankingReward: IMTimeattackRankingReward): Observable<EntityResponseType> {
    return this.http.put<IMTimeattackRankingReward>(this.resourceUrl, mTimeattackRankingReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTimeattackRankingReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTimeattackRankingReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
