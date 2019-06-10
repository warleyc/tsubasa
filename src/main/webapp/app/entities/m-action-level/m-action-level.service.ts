import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMActionLevel } from 'app/shared/model/m-action-level.model';

type EntityResponseType = HttpResponse<IMActionLevel>;
type EntityArrayResponseType = HttpResponse<IMActionLevel[]>;

@Injectable({ providedIn: 'root' })
export class MActionLevelService {
  public resourceUrl = SERVER_API_URL + 'api/m-action-levels';

  constructor(protected http: HttpClient) {}

  create(mActionLevel: IMActionLevel): Observable<EntityResponseType> {
    return this.http.post<IMActionLevel>(this.resourceUrl, mActionLevel, { observe: 'response' });
  }

  update(mActionLevel: IMActionLevel): Observable<EntityResponseType> {
    return this.http.put<IMActionLevel>(this.resourceUrl, mActionLevel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMActionLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMActionLevel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
