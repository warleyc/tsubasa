import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

type EntityResponseType = HttpResponse<IMTargetActionGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetActionGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetActionGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-action-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetActionGroup: IMTargetActionGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetActionGroup>(this.resourceUrl, mTargetActionGroup, { observe: 'response' });
  }

  update(mTargetActionGroup: IMTargetActionGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetActionGroup>(this.resourceUrl, mTargetActionGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetActionGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetActionGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
