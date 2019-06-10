import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMBadge } from 'app/shared/model/m-badge.model';

type EntityResponseType = HttpResponse<IMBadge>;
type EntityArrayResponseType = HttpResponse<IMBadge[]>;

@Injectable({ providedIn: 'root' })
export class MBadgeService {
  public resourceUrl = SERVER_API_URL + 'api/m-badges';

  constructor(protected http: HttpClient) {}

  create(mBadge: IMBadge): Observable<EntityResponseType> {
    return this.http.post<IMBadge>(this.resourceUrl, mBadge, { observe: 'response' });
  }

  update(mBadge: IMBadge): Observable<EntityResponseType> {
    return this.http.put<IMBadge>(this.resourceUrl, mBadge, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMBadge>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMBadge[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
