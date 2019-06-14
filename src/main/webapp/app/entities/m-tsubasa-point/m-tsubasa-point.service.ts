import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';

type EntityResponseType = HttpResponse<IMTsubasaPoint>;
type EntityArrayResponseType = HttpResponse<IMTsubasaPoint[]>;

@Injectable({ providedIn: 'root' })
export class MTsubasaPointService {
  public resourceUrl = SERVER_API_URL + 'api/m-tsubasa-points';

  constructor(protected http: HttpClient) {}

  create(mTsubasaPoint: IMTsubasaPoint): Observable<EntityResponseType> {
    return this.http.post<IMTsubasaPoint>(this.resourceUrl, mTsubasaPoint, { observe: 'response' });
  }

  update(mTsubasaPoint: IMTsubasaPoint): Observable<EntityResponseType> {
    return this.http.put<IMTsubasaPoint>(this.resourceUrl, mTsubasaPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTsubasaPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTsubasaPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
