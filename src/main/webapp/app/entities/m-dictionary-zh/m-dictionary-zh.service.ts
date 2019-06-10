import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';

type EntityResponseType = HttpResponse<IMDictionaryZh>;
type EntityArrayResponseType = HttpResponse<IMDictionaryZh[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryZhService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-zhs';

  constructor(protected http: HttpClient) {}

  create(mDictionaryZh: IMDictionaryZh): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryZh>(this.resourceUrl, mDictionaryZh, { observe: 'response' });
  }

  update(mDictionaryZh: IMDictionaryZh): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryZh>(this.resourceUrl, mDictionaryZh, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryZh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryZh[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
