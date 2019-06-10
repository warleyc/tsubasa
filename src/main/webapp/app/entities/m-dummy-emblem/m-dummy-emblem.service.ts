import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

type EntityResponseType = HttpResponse<IMDummyEmblem>;
type EntityArrayResponseType = HttpResponse<IMDummyEmblem[]>;

@Injectable({ providedIn: 'root' })
export class MDummyEmblemService {
  public resourceUrl = SERVER_API_URL + 'api/m-dummy-emblems';

  constructor(protected http: HttpClient) {}

  create(mDummyEmblem: IMDummyEmblem): Observable<EntityResponseType> {
    return this.http.post<IMDummyEmblem>(this.resourceUrl, mDummyEmblem, { observe: 'response' });
  }

  update(mDummyEmblem: IMDummyEmblem): Observable<EntityResponseType> {
    return this.http.put<IMDummyEmblem>(this.resourceUrl, mDummyEmblem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDummyEmblem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDummyEmblem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
