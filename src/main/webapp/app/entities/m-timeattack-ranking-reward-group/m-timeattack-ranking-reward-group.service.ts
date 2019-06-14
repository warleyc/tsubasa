import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';

type EntityResponseType = HttpResponse<IMTimeattackRankingRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMTimeattackRankingRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTimeattackRankingRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-timeattack-ranking-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMTimeattackRankingRewardGroup>(this.resourceUrl, mTimeattackRankingRewardGroup, { observe: 'response' });
  }

  update(mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMTimeattackRankingRewardGroup>(this.resourceUrl, mTimeattackRankingRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTimeattackRankingRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTimeattackRankingRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
