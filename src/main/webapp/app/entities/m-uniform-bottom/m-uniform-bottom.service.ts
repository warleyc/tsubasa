import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

type EntityResponseType = HttpResponse<IMUniformBottom>;
type EntityArrayResponseType = HttpResponse<IMUniformBottom[]>;

@Injectable({ providedIn: 'root' })
export class MUniformBottomService {
  public resourceUrl = SERVER_API_URL + 'api/m-uniform-bottoms';

  constructor(protected http: HttpClient) {}

  create(mUniformBottom: IMUniformBottom): Observable<EntityResponseType> {
    return this.http.post<IMUniformBottom>(this.resourceUrl, mUniformBottom, { observe: 'response' });
  }

  update(mUniformBottom: IMUniformBottom): Observable<EntityResponseType> {
    return this.http.put<IMUniformBottom>(this.resourceUrl, mUniformBottom, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMUniformBottom>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMUniformBottom[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
