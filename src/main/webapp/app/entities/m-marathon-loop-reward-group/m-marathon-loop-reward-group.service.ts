import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';

type EntityResponseType = HttpResponse<IMMarathonLoopRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMMarathonLoopRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonLoopRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-loop-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMMarathonLoopRewardGroup>(this.resourceUrl, mMarathonLoopRewardGroup, { observe: 'response' });
  }

  update(mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMMarathonLoopRewardGroup>(this.resourceUrl, mMarathonLoopRewardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonLoopRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonLoopRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
