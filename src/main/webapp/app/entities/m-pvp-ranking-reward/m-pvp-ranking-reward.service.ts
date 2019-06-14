import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

type EntityResponseType = HttpResponse<IMPvpRankingReward>;
type EntityArrayResponseType = HttpResponse<IMPvpRankingReward[]>;

@Injectable({ providedIn: 'root' })
export class MPvpRankingRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-ranking-rewards';

  constructor(protected http: HttpClient) {}

  create(mPvpRankingReward: IMPvpRankingReward): Observable<EntityResponseType> {
    return this.http.post<IMPvpRankingReward>(this.resourceUrl, mPvpRankingReward, { observe: 'response' });
  }

  update(mPvpRankingReward: IMPvpRankingReward): Observable<EntityResponseType> {
    return this.http.put<IMPvpRankingReward>(this.resourceUrl, mPvpRankingReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpRankingReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpRankingReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
