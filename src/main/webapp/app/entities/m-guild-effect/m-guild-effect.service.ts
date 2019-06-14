import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildEffect } from 'app/shared/model/m-guild-effect.model';

type EntityResponseType = HttpResponse<IMGuildEffect>;
type EntityArrayResponseType = HttpResponse<IMGuildEffect[]>;

@Injectable({ providedIn: 'root' })
export class MGuildEffectService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-effects';

  constructor(protected http: HttpClient) {}

  create(mGuildEffect: IMGuildEffect): Observable<EntityResponseType> {
    return this.http.post<IMGuildEffect>(this.resourceUrl, mGuildEffect, { observe: 'response' });
  }

  update(mGuildEffect: IMGuildEffect): Observable<EntityResponseType> {
    return this.http.put<IMGuildEffect>(this.resourceUrl, mGuildEffect, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildEffect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildEffect[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
