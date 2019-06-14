import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpGrade } from 'app/shared/model/m-pvp-grade.model';

type EntityResponseType = HttpResponse<IMPvpGrade>;
type EntityArrayResponseType = HttpResponse<IMPvpGrade[]>;

@Injectable({ providedIn: 'root' })
export class MPvpGradeService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-grades';

  constructor(protected http: HttpClient) {}

  create(mPvpGrade: IMPvpGrade): Observable<EntityResponseType> {
    return this.http.post<IMPvpGrade>(this.resourceUrl, mPvpGrade, { observe: 'response' });
  }

  update(mPvpGrade: IMPvpGrade): Observable<EntityResponseType> {
    return this.http.put<IMPvpGrade>(this.resourceUrl, mPvpGrade, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpGrade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpGrade[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
