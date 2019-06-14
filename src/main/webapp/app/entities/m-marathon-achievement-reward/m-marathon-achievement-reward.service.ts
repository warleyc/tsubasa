import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';

type EntityResponseType = HttpResponse<IMMarathonAchievementReward>;
type EntityArrayResponseType = HttpResponse<IMMarathonAchievementReward[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonAchievementRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-achievement-rewards';

  constructor(protected http: HttpClient) {}

  create(mMarathonAchievementReward: IMMarathonAchievementReward): Observable<EntityResponseType> {
    return this.http.post<IMMarathonAchievementReward>(this.resourceUrl, mMarathonAchievementReward, { observe: 'response' });
  }

  update(mMarathonAchievementReward: IMMarathonAchievementReward): Observable<EntityResponseType> {
    return this.http.put<IMMarathonAchievementReward>(this.resourceUrl, mMarathonAchievementReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonAchievementReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonAchievementReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
