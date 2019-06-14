import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeagueEffect } from 'app/shared/model/m-league-effect.model';

type EntityResponseType = HttpResponse<IMLeagueEffect>;
type EntityArrayResponseType = HttpResponse<IMLeagueEffect[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueEffectService {
  public resourceUrl = SERVER_API_URL + 'api/m-league-effects';

  constructor(protected http: HttpClient) {}

  create(mLeagueEffect: IMLeagueEffect): Observable<EntityResponseType> {
    return this.http.post<IMLeagueEffect>(this.resourceUrl, mLeagueEffect, { observe: 'response' });
  }

  update(mLeagueEffect: IMLeagueEffect): Observable<EntityResponseType> {
    return this.http.put<IMLeagueEffect>(this.resourceUrl, mLeagueEffect, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeagueEffect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeagueEffect[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
