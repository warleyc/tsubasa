import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';

type EntityResponseType = HttpResponse<IMTargetActionTypeGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetActionTypeGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetActionTypeGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-action-type-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetActionTypeGroup: IMTargetActionTypeGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetActionTypeGroup>(this.resourceUrl, mTargetActionTypeGroup, { observe: 'response' });
  }

  update(mTargetActionTypeGroup: IMTargetActionTypeGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetActionTypeGroup>(this.resourceUrl, mTargetActionTypeGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetActionTypeGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetActionTypeGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
