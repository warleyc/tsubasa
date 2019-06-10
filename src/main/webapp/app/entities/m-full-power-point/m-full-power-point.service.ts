import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMFullPowerPoint } from 'app/shared/model/m-full-power-point.model';

type EntityResponseType = HttpResponse<IMFullPowerPoint>;
type EntityArrayResponseType = HttpResponse<IMFullPowerPoint[]>;

@Injectable({ providedIn: 'root' })
export class MFullPowerPointService {
  public resourceUrl = SERVER_API_URL + 'api/m-full-power-points';

  constructor(protected http: HttpClient) {}

  create(mFullPowerPoint: IMFullPowerPoint): Observable<EntityResponseType> {
    return this.http.post<IMFullPowerPoint>(this.resourceUrl, mFullPowerPoint, { observe: 'response' });
  }

  update(mFullPowerPoint: IMFullPowerPoint): Observable<EntityResponseType> {
    return this.http.put<IMFullPowerPoint>(this.resourceUrl, mFullPowerPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMFullPowerPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMFullPowerPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
