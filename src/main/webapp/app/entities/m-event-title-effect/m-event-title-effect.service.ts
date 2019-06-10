import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';

type EntityResponseType = HttpResponse<IMEventTitleEffect>;
type EntityArrayResponseType = HttpResponse<IMEventTitleEffect[]>;

@Injectable({ providedIn: 'root' })
export class MEventTitleEffectService {
  public resourceUrl = SERVER_API_URL + 'api/m-event-title-effects';

  constructor(protected http: HttpClient) {}

  create(mEventTitleEffect: IMEventTitleEffect): Observable<EntityResponseType> {
    return this.http.post<IMEventTitleEffect>(this.resourceUrl, mEventTitleEffect, { observe: 'response' });
  }

  update(mEventTitleEffect: IMEventTitleEffect): Observable<EntityResponseType> {
    return this.http.put<IMEventTitleEffect>(this.resourceUrl, mEventTitleEffect, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEventTitleEffect>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEventTitleEffect[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
