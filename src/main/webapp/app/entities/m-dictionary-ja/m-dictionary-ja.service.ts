import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';

type EntityResponseType = HttpResponse<IMDictionaryJa>;
type EntityArrayResponseType = HttpResponse<IMDictionaryJa[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryJaService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-jas';

  constructor(protected http: HttpClient) {}

  create(mDictionaryJa: IMDictionaryJa): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryJa>(this.resourceUrl, mDictionaryJa, { observe: 'response' });
  }

  update(mDictionaryJa: IMDictionaryJa): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryJa>(this.resourceUrl, mDictionaryJa, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryJa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryJa[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
