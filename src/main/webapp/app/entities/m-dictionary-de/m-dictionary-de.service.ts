import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryDe } from 'app/shared/model/m-dictionary-de.model';

type EntityResponseType = HttpResponse<IMDictionaryDe>;
type EntityArrayResponseType = HttpResponse<IMDictionaryDe[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryDeService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-des';

  constructor(protected http: HttpClient) {}

  create(mDictionaryDe: IMDictionaryDe): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryDe>(this.resourceUrl, mDictionaryDe, { observe: 'response' });
  }

  update(mDictionaryDe: IMDictionaryDe): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryDe>(this.resourceUrl, mDictionaryDe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryDe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryDe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
