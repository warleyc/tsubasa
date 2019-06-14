import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

type EntityResponseType = HttpResponse<IMUniformOriginalSet>;
type EntityArrayResponseType = HttpResponse<IMUniformOriginalSet[]>;

@Injectable({ providedIn: 'root' })
export class MUniformOriginalSetService {
  public resourceUrl = SERVER_API_URL + 'api/m-uniform-original-sets';

  constructor(protected http: HttpClient) {}

  create(mUniformOriginalSet: IMUniformOriginalSet): Observable<EntityResponseType> {
    return this.http.post<IMUniformOriginalSet>(this.resourceUrl, mUniformOriginalSet, { observe: 'response' });
  }

  update(mUniformOriginalSet: IMUniformOriginalSet): Observable<EntityResponseType> {
    return this.http.put<IMUniformOriginalSet>(this.resourceUrl, mUniformOriginalSet, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMUniformOriginalSet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMUniformOriginalSet[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
