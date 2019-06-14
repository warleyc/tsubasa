import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';

type EntityResponseType = HttpResponse<IMTeamEffectLevel>;
type EntityArrayResponseType = HttpResponse<IMTeamEffectLevel[]>;

@Injectable({ providedIn: 'root' })
export class MTeamEffectLevelService {
  public resourceUrl = SERVER_API_URL + 'api/m-team-effect-levels';

  constructor(protected http: HttpClient) {}

  create(mTeamEffectLevel: IMTeamEffectLevel): Observable<EntityResponseType> {
    return this.http.post<IMTeamEffectLevel>(this.resourceUrl, mTeamEffectLevel, { observe: 'response' });
  }

  update(mTeamEffectLevel: IMTeamEffectLevel): Observable<EntityResponseType> {
    return this.http.put<IMTeamEffectLevel>(this.resourceUrl, mTeamEffectLevel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTeamEffectLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTeamEffectLevel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
