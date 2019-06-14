import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';

type EntityResponseType = HttpResponse<IMPvpRegulation>;
type EntityArrayResponseType = HttpResponse<IMPvpRegulation[]>;

@Injectable({ providedIn: 'root' })
export class MPvpRegulationService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-regulations';

  constructor(protected http: HttpClient) {}

  create(mPvpRegulation: IMPvpRegulation): Observable<EntityResponseType> {
    return this.http.post<IMPvpRegulation>(this.resourceUrl, mPvpRegulation, { observe: 'response' });
  }

  update(mPvpRegulation: IMPvpRegulation): Observable<EntityResponseType> {
    return this.http.put<IMPvpRegulation>(this.resourceUrl, mPvpRegulation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpRegulation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpRegulation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
