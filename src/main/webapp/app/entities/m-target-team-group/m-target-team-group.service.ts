import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

type EntityResponseType = HttpResponse<IMTargetTeamGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetTeamGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetTeamGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-team-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetTeamGroup: IMTargetTeamGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetTeamGroup>(this.resourceUrl, mTargetTeamGroup, { observe: 'response' });
  }

  update(mTargetTeamGroup: IMTargetTeamGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetTeamGroup>(this.resourceUrl, mTargetTeamGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetTeamGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetTeamGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
