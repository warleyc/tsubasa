import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';

type EntityResponseType = HttpResponse<IMLeagueRankingReward>;
type EntityArrayResponseType = HttpResponse<IMLeagueRankingReward[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueRankingRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-league-ranking-rewards';

  constructor(protected http: HttpClient) {}

  create(mLeagueRankingReward: IMLeagueRankingReward): Observable<EntityResponseType> {
    return this.http.post<IMLeagueRankingReward>(this.resourceUrl, mLeagueRankingReward, { observe: 'response' });
  }

  update(mLeagueRankingReward: IMLeagueRankingReward): Observable<EntityResponseType> {
    return this.http.put<IMLeagueRankingReward>(this.resourceUrl, mLeagueRankingReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeagueRankingReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeagueRankingReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
