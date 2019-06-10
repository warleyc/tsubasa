import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryIt } from 'app/shared/model/m-dictionary-it.model';

type EntityResponseType = HttpResponse<IMDictionaryIt>;
type EntityArrayResponseType = HttpResponse<IMDictionaryIt[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryItService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-its';

  constructor(protected http: HttpClient) {}

  create(mDictionaryIt: IMDictionaryIt): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryIt>(this.resourceUrl, mDictionaryIt, { observe: 'response' });
  }

  update(mDictionaryIt: IMDictionaryIt): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryIt>(this.resourceUrl, mDictionaryIt, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryIt>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryIt[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
