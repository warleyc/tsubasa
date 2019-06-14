import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';

type EntityResponseType = HttpResponse<IMPvpWatcherStamp>;
type EntityArrayResponseType = HttpResponse<IMPvpWatcherStamp[]>;

@Injectable({ providedIn: 'root' })
export class MPvpWatcherStampService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-watcher-stamps';

  constructor(protected http: HttpClient) {}

  create(mPvpWatcherStamp: IMPvpWatcherStamp): Observable<EntityResponseType> {
    return this.http.post<IMPvpWatcherStamp>(this.resourceUrl, mPvpWatcherStamp, { observe: 'response' });
  }

  update(mPvpWatcherStamp: IMPvpWatcherStamp): Observable<EntityResponseType> {
    return this.http.put<IMPvpWatcherStamp>(this.resourceUrl, mPvpWatcherStamp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpWatcherStamp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpWatcherStamp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
