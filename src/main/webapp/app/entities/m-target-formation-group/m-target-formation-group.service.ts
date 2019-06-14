import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

type EntityResponseType = HttpResponse<IMTargetFormationGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetFormationGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetFormationGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-formation-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetFormationGroup: IMTargetFormationGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetFormationGroup>(this.resourceUrl, mTargetFormationGroup, { observe: 'response' });
  }

  update(mTargetFormationGroup: IMTargetFormationGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetFormationGroup>(this.resourceUrl, mTargetFormationGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetFormationGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetFormationGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
