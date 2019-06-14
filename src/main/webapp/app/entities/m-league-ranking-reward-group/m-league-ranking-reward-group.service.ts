import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';

type EntityResponseType = HttpResponse<IMLeagueRankingRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMLeagueRankingRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueRankingRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-league-ranking-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMLeagueRankingRewardGroup>(this.resourceUrl, mLeagueRankingRewardGroup, { observe: 'response' });
  }

  update(mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMLeagueRankingRewardGroup>(this.resourceUrl, mLeagueRankingRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeagueRankingRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeagueRankingRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
