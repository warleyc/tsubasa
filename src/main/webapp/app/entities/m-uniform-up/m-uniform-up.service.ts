import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMUniformUp } from 'app/shared/model/m-uniform-up.model';

type EntityResponseType = HttpResponse<IMUniformUp>;
type EntityArrayResponseType = HttpResponse<IMUniformUp[]>;

@Injectable({ providedIn: 'root' })
export class MUniformUpService {
  public resourceUrl = SERVER_API_URL + 'api/m-uniform-ups';

  constructor(protected http: HttpClient) {}

  create(mUniformUp: IMUniformUp): Observable<EntityResponseType> {
    return this.http.post<IMUniformUp>(this.resourceUrl, mUniformUp, { observe: 'response' });
  }

  update(mUniformUp: IMUniformUp): Observable<EntityResponseType> {
    return this.http.put<IMUniformUp>(this.resourceUrl, mUniformUp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMUniformUp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMUniformUp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
