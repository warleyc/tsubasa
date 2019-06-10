import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

type EntityResponseType = HttpResponse<IMDistributeParamPoint>;
type EntityArrayResponseType = HttpResponse<IMDistributeParamPoint[]>;

@Injectable({ providedIn: 'root' })
export class MDistributeParamPointService {
  public resourceUrl = SERVER_API_URL + 'api/m-distribute-param-points';

  constructor(protected http: HttpClient) {}

  create(mDistributeParamPoint: IMDistributeParamPoint): Observable<EntityResponseType> {
    return this.http.post<IMDistributeParamPoint>(this.resourceUrl, mDistributeParamPoint, { observe: 'response' });
  }

  update(mDistributeParamPoint: IMDistributeParamPoint): Observable<EntityResponseType> {
    return this.http.put<IMDistributeParamPoint>(this.resourceUrl, mDistributeParamPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDistributeParamPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDistributeParamPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
