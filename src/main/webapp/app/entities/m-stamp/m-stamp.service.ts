import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMStamp } from 'app/shared/model/m-stamp.model';

type EntityResponseType = HttpResponse<IMStamp>;
type EntityArrayResponseType = HttpResponse<IMStamp[]>;

@Injectable({ providedIn: 'root' })
export class MStampService {
  public resourceUrl = SERVER_API_URL + 'api/m-stamps';

  constructor(protected http: HttpClient) {}

  create(mStamp: IMStamp): Observable<EntityResponseType> {
    return this.http.post<IMStamp>(this.resourceUrl, mStamp, { observe: 'response' });
  }

  update(mStamp: IMStamp): Observable<EntityResponseType> {
    return this.http.put<IMStamp>(this.resourceUrl, mStamp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMStamp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMStamp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
