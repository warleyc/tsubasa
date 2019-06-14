import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMasterVersion } from 'app/shared/model/m-master-version.model';

type EntityResponseType = HttpResponse<IMMasterVersion>;
type EntityArrayResponseType = HttpResponse<IMMasterVersion[]>;

@Injectable({ providedIn: 'root' })
export class MMasterVersionService {
  public resourceUrl = SERVER_API_URL + 'api/m-master-versions';

  constructor(protected http: HttpClient) {}

  create(mMasterVersion: IMMasterVersion): Observable<EntityResponseType> {
    return this.http.post<IMMasterVersion>(this.resourceUrl, mMasterVersion, { observe: 'response' });
  }

  update(mMasterVersion: IMMasterVersion): Observable<EntityResponseType> {
    return this.http.put<IMMasterVersion>(this.resourceUrl, mMasterVersion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMasterVersion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMasterVersion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
