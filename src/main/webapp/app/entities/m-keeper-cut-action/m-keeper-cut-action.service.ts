import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';

type EntityResponseType = HttpResponse<IMKeeperCutAction>;
type EntityArrayResponseType = HttpResponse<IMKeeperCutAction[]>;

@Injectable({ providedIn: 'root' })
export class MKeeperCutActionService {
  public resourceUrl = SERVER_API_URL + 'api/m-keeper-cut-actions';

  constructor(protected http: HttpClient) {}

  create(mKeeperCutAction: IMKeeperCutAction): Observable<EntityResponseType> {
    return this.http.post<IMKeeperCutAction>(this.resourceUrl, mKeeperCutAction, { observe: 'response' });
  }

  update(mKeeperCutAction: IMKeeperCutAction): Observable<EntityResponseType> {
    return this.http.put<IMKeeperCutAction>(this.resourceUrl, mKeeperCutAction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMKeeperCutAction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMKeeperCutAction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
