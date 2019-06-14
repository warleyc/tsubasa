import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildLevel } from 'app/shared/model/m-guild-level.model';

type EntityResponseType = HttpResponse<IMGuildLevel>;
type EntityArrayResponseType = HttpResponse<IMGuildLevel[]>;

@Injectable({ providedIn: 'root' })
export class MGuildLevelService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-levels';

  constructor(protected http: HttpClient) {}

  create(mGuildLevel: IMGuildLevel): Observable<EntityResponseType> {
    return this.http.post<IMGuildLevel>(this.resourceUrl, mGuildLevel, { observe: 'response' });
  }

  update(mGuildLevel: IMGuildLevel): Observable<EntityResponseType> {
    return this.http.put<IMGuildLevel>(this.resourceUrl, mGuildLevel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildLevel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
