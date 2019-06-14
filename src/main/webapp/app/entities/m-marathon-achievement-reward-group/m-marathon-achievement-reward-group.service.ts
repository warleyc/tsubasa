import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';

type EntityResponseType = HttpResponse<IMMarathonAchievementRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMMarathonAchievementRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonAchievementRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-achievement-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMMarathonAchievementRewardGroup>(this.resourceUrl, mMarathonAchievementRewardGroup, { observe: 'response' });
  }

  update(mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMMarathonAchievementRewardGroup>(this.resourceUrl, mMarathonAchievementRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonAchievementRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonAchievementRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
