import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';

type EntityResponseType = HttpResponse<IMDictionaryFr>;
type EntityArrayResponseType = HttpResponse<IMDictionaryFr[]>;

@Injectable({ providedIn: 'root' })
export class MDictionaryFrService {
  public resourceUrl = SERVER_API_URL + 'api/m-dictionary-frs';

  constructor(protected http: HttpClient) {}

  create(mDictionaryFr: IMDictionaryFr): Observable<EntityResponseType> {
    return this.http.post<IMDictionaryFr>(this.resourceUrl, mDictionaryFr, { observe: 'response' });
  }

  update(mDictionaryFr: IMDictionaryFr): Observable<EntityResponseType> {
    return this.http.put<IMDictionaryFr>(this.resourceUrl, mDictionaryFr, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDictionaryFr>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDictionaryFr[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
