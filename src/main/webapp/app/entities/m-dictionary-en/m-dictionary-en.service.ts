import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryEn } from 'app/shared/model/m-dictionary-en.model';

type EntityResponseType = HttpResponse<IMDictionaryEn>;
type EntityArrayResponseType = HttpResponse<IMDictionaryEn[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryEnService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-ens';

  constructor(protected http: HttpClient) {}

  create(mDictionaryEn: IMDictionaryEn): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryEn>(this.resourceUrl, mDictionaryEn, { observe: 'response' });
  }

  update(mDictionaryEn: IMDictionaryEn): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryEn>(this.resourceUrl, mDictionaryEn, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryEn>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryEn[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
