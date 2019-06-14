import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLuck } from 'app/shared/model/m-luck.model';

type EntityResponseType = HttpResponse<IMLuck>;
type EntityArrayResponseType = HttpResponse<IMLuck[]>;

@Injectable({ providedIn: 'root' })
export class MLuckService {
  public resourceUrl = SERVER_API_URL + 'api/m-lucks';

  constructor(protected http: HttpClient) {}

  create(mLuck: IMLuck): Observable<EntityResponseType> {
    return this.http.post<IMLuck>(this.resourceUrl, mLuck, { observe: 'response' });
  }

  update(mLuck: IMLuck): Observable<EntityResponseType> {
    return this.http.put<IMLuck>(this.resourceUrl, mLuck, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLuck>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLuck[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
