import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';

type EntityResponseType = HttpResponse<IMDictionaryPt>;
type EntityArrayResponseType = HttpResponse<IMDictionaryPt[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryPtService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-pts';

  constructor(protected http: HttpClient) {}

  create(mDictionaryPt: IMDictionaryPt): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryPt>(this.resourceUrl, mDictionaryPt, { observe: 'response' });
  }

  update(mDictionaryPt: IMDictionaryPt): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryPt>(this.resourceUrl, mDictionaryPt, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryPt>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryPt[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
