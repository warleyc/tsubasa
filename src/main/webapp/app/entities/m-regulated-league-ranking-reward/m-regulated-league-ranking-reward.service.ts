import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';

type EntityResponseType = HttpResponse<IMRegulatedLeagueRankingReward>;
type EntityArrayResponseType = HttpResponse<IMRegulatedLeagueRankingReward[]>;

@Injectable({ providedIn: 'root' })
export class MRegulatedLeagueRankingRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-regulated-league-ranking-rewards';

  constructor(protected http: HttpClient) {}

  create(mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward): Observable<EntityResponseType> {
    return this.http.post<IMRegulatedLeagueRankingReward>(this.resourceUrl, mRegulatedLeagueRankingReward, { observe: 'response' });
  }

  update(mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward): Observable<EntityResponseType> {
    return this.http.put<IMRegulatedLeagueRankingReward>(this.resourceUrl, mRegulatedLeagueRankingReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRegulatedLeagueRankingReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRegulatedLeagueRankingReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
