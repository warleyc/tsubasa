import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

type EntityResponseType = HttpResponse<IMDummyOpponent>;
type EntityArrayResponseType = HttpResponse<IMDummyOpponent[]>;

@Injectable({ providedIn: 'root' })
export class MDummyOpponentService {
  public resourceUrl = SERVER_API_URL + 'api/m-dummy-opponents';

  constructor(protected http: HttpClient) {}

  create(mDummyOpponent: IMDummyOpponent): Observable<EntityResponseType> {
    return this.http.post<IMDummyOpponent>(this.resourceUrl, mDummyOpponent, { observe: 'response' });
  }

  update(mDummyOpponent: IMDummyOpponent): Observable<EntityResponseType> {
    return this.http.put<IMDummyOpponent>(this.resourceUrl, mDummyOpponent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDummyOpponent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDummyOpponent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
