import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

type EntityResponseType = HttpResponse<IMPvpGradeRequirement>;
type EntityArrayResponseType = HttpResponse<IMPvpGradeRequirement[]>;

@Injectable({ providedIn: 'root' })
export class MPvpGradeRequirementService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-grade-requirements';

  constructor(protected http: HttpClient) {}

  create(mPvpGradeRequirement: IMPvpGradeRequirement): Observable<EntityResponseType> {
    return this.http.post<IMPvpGradeRequirement>(this.resourceUrl, mPvpGradeRequirement, { observe: 'response' });
  }

  update(mPvpGradeRequirement: IMPvpGradeRequirement): Observable<EntityResponseType> {
    return this.http.put<IMPvpGradeRequirement>(this.resourceUrl, mPvpGradeRequirement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpGradeRequirement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpGradeRequirement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
