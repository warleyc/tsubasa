import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardLevel } from 'app/shared/model/m-card-level.model';

type EntityResponseType = HttpResponse<IMCardLevel>;
type EntityArrayResponseType = HttpResponse<IMCardLevel[]>;

@Injectable({ providedIn: 'root' })
export class MCardLevelService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-levels';

  constructor(protected http: HttpClient) {}

  create(mCardLevel: IMCardLevel): Observable<EntityResponseType> {
    return this.http.post<IMCardLevel>(this.resourceUrl, mCardLevel, { observe: 'response' });
  }

  update(mCardLevel: IMCardLevel): Observable<EntityResponseType> {
    return this.http.put<IMCardLevel>(this.resourceUrl, mCardLevel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardLevel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
