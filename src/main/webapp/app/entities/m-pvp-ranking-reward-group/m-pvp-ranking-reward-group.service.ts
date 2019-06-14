import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';

type EntityResponseType = HttpResponse<IMPvpRankingRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMPvpRankingRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MPvpRankingRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-ranking-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mPvpRankingRewardGroup: IMPvpRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMPvpRankingRewardGroup>(this.resourceUrl, mPvpRankingRewardGroup, { observe: 'response' });
  }

  update(mPvpRankingRewardGroup: IMPvpRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMPvpRankingRewardGroup>(this.resourceUrl, mPvpRankingRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpRankingRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpRankingRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
