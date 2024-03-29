import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeague } from 'app/shared/model/m-league.model';

type EntityResponseType = HttpResponse<IMLeague>;
type EntityArrayResponseType = HttpResponse<IMLeague[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueService {
  public resourceUrl = SERVER_API_URL + 'api/m-leagues';

  constructor(protected http: HttpClient) {}

  create(mLeague: IMLeague): Observable<EntityResponseType> {
    return this.http.post<IMLeague>(this.resourceUrl, mLeague, { observe: 'response' });
  }

  update(mLeague: IMLeague): Observable<EntityResponseType> {
    return this.http.put<IMLeague>(this.resourceUrl, mLeague, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeague>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeague[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
