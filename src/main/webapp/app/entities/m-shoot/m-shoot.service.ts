import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMShoot } from 'app/shared/model/m-shoot.model';

type EntityResponseType = HttpResponse<IMShoot>;
type EntityArrayResponseType = HttpResponse<IMShoot[]>;

@Injectable({ providedIn: 'root' })
export class MShootService {
  public resourceUrl = SERVER_API_URL + 'api/m-shoots';

  constructor(protected http: HttpClient) {}

  create(mShoot: IMShoot): Observable<EntityResponseType> {
    return this.http.post<IMShoot>(this.resourceUrl, mShoot, { observe: 'response' });
  }

  update(mShoot: IMShoot): Observable<EntityResponseType> {
    return this.http.put<IMShoot>(this.resourceUrl, mShoot, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMShoot>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMShoot[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
