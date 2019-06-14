import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

type EntityResponseType = HttpResponse<IMTeamEffectBase>;
type EntityArrayResponseType = HttpResponse<IMTeamEffectBase[]>;

@Injectable({ providedIn: 'root' })
export class MTeamEffectBaseService {
  public resourceUrl = SERVER_API_URL + 'api/m-team-effect-bases';

  constructor(protected http: HttpClient) {}

  create(mTeamEffectBase: IMTeamEffectBase): Observable<EntityResponseType> {
    return this.http.post<IMTeamEffectBase>(this.resourceUrl, mTeamEffectBase, { observe: 'response' });
  }

  update(mTeamEffectBase: IMTeamEffectBase): Observable<EntityResponseType> {
    return this.http.put<IMTeamEffectBase>(this.resourceUrl, mTeamEffectBase, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTeamEffectBase>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTeamEffectBase[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
