import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMatchEnvironment } from 'app/shared/model/m-match-environment.model';

type EntityResponseType = HttpResponse<IMMatchEnvironment>;
type EntityArrayResponseType = HttpResponse<IMMatchEnvironment[]>;

@Injectable({ providedIn: 'root' })
export class MMatchEnvironmentService {
  public resourceUrl = SERVER_API_URL + 'api/m-match-environments';

  constructor(protected http: HttpClient) {}

  create(mMatchEnvironment: IMMatchEnvironment): Observable<EntityResponseType> {
    return this.http.post<IMMatchEnvironment>(this.resourceUrl, mMatchEnvironment, { observe: 'response' });
  }

  update(mMatchEnvironment: IMMatchEnvironment): Observable<EntityResponseType> {
    return this.http.put<IMMatchEnvironment>(this.resourceUrl, mMatchEnvironment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMatchEnvironment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMatchEnvironment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
