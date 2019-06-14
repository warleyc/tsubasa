import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';

type EntityResponseType = HttpResponse<IMQuestRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMQuestRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MQuestRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mQuestRewardGroup: IMQuestRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMQuestRewardGroup>(this.resourceUrl, mQuestRewardGroup, { observe: 'response' });
  }

  update(mQuestRewardGroup: IMQuestRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMQuestRewardGroup>(this.resourceUrl, mQuestRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
