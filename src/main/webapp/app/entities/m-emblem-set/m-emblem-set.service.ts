import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEmblemSet } from 'app/shared/model/m-emblem-set.model';

type EntityResponseType = HttpResponse<IMEmblemSet>;
type EntityArrayResponseType = HttpResponse<IMEmblemSet[]>;

@Injectable({ providedIn: 'root' })
export class MEmblemSetService {
  public resourceUrl = SERVER_API_URL + 'api/m-emblem-sets';

  constructor(protected http: HttpClient) {}

  create(mEmblemSet: IMEmblemSet): Observable<EntityResponseType> {
    return this.http.post<IMEmblemSet>(this.resourceUrl, mEmblemSet, { observe: 'response' });
  }

  update(mEmblemSet: IMEmblemSet): Observable<EntityResponseType> {
    return this.http.put<IMEmblemSet>(this.resourceUrl, mEmblemSet, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEmblemSet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEmblemSet[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
