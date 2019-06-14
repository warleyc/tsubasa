import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';

type EntityResponseType = HttpResponse<IMLeagueAffiliateReward>;
type EntityArrayResponseType = HttpResponse<IMLeagueAffiliateReward[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueAffiliateRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-league-affiliate-rewards';

  constructor(protected http: HttpClient) {}

  create(mLeagueAffiliateReward: IMLeagueAffiliateReward): Observable<EntityResponseType> {
    return this.http.post<IMLeagueAffiliateReward>(this.resourceUrl, mLeagueAffiliateReward, { observe: 'response' });
  }

  update(mLeagueAffiliateReward: IMLeagueAffiliateReward): Observable<EntityResponseType> {
    return this.http.put<IMLeagueAffiliateReward>(this.resourceUrl, mLeagueAffiliateReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeagueAffiliateReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeagueAffiliateReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
