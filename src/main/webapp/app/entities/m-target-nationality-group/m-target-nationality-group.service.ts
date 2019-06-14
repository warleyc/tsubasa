import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

type EntityResponseType = HttpResponse<IMTargetNationalityGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetNationalityGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetNationalityGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-nationality-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetNationalityGroup: IMTargetNationalityGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetNationalityGroup>(this.resourceUrl, mTargetNationalityGroup, { observe: 'response' });
  }

  update(mTargetNationalityGroup: IMTargetNationalityGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetNationalityGroup>(this.resourceUrl, mTargetNationalityGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetNationalityGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetNationalityGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
