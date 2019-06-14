import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';

type EntityResponseType = HttpResponse<IMGuildEffectLevel>;
type EntityArrayResponseType = HttpResponse<IMGuildEffectLevel[]>;

@Injectable({ providedIn: 'root' })
export class MGuildEffectLevelService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-effect-levels';

  constructor(protected http: HttpClient) {}

  create(mGuildEffectLevel: IMGuildEffectLevel): Observable<EntityResponseType> {
    return this.http.post<IMGuildEffectLevel>(this.resourceUrl, mGuildEffectLevel, { observe: 'response' });
  }

  update(mGuildEffectLevel: IMGuildEffectLevel): Observable<EntityResponseType> {
    return this.http.put<IMGuildEffectLevel>(this.resourceUrl, mGuildEffectLevel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildEffectLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildEffectLevel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
