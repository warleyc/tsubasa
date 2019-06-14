import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';

type EntityResponseType = HttpResponse<IMPvpPlayerStamp>;
type EntityArrayResponseType = HttpResponse<IMPvpPlayerStamp[]>;

@Injectable({ providedIn: 'root' })
export class MPvpPlayerStampService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-player-stamps';

  constructor(protected http: HttpClient) {}

  create(mPvpPlayerStamp: IMPvpPlayerStamp): Observable<EntityResponseType> {
    return this.http.post<IMPvpPlayerStamp>(this.resourceUrl, mPvpPlayerStamp, { observe: 'response' });
  }

  update(mPvpPlayerStamp: IMPvpPlayerStamp): Observable<EntityResponseType> {
    return this.http.put<IMPvpPlayerStamp>(this.resourceUrl, mPvpPlayerStamp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpPlayerStamp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpPlayerStamp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
