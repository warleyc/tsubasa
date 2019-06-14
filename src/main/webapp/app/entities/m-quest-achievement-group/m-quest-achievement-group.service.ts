import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';

type EntityResponseType = HttpResponse<IMQuestAchievementGroup>;
type EntityArrayResponseType = HttpResponse<IMQuestAchievementGroup[]>;

@Injectable({ providedIn: 'root' })
export class MQuestAchievementGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-achievement-groups';

  constructor(protected http: HttpClient) {}

  create(mQuestAchievementGroup: IMQuestAchievementGroup): Observable<EntityResponseType> {
    return this.http.post<IMQuestAchievementGroup>(this.resourceUrl, mQuestAchievementGroup, { observe: 'response' });
  }

  update(mQuestAchievementGroup: IMQuestAchievementGroup): Observable<EntityResponseType> {
    return this.http.put<IMQuestAchievementGroup>(this.resourceUrl, mQuestAchievementGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestAchievementGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestAchievementGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
