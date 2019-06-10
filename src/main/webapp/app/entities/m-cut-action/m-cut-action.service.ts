import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCutAction } from 'app/shared/model/m-cut-action.model';

type EntityResponseType = HttpResponse<IMCutAction>;
type EntityArrayResponseType = HttpResponse<IMCutAction[]>;

@Injectable({ providedIn: 'root' })
export class MCutActionService {
  public resourceUrl = SERVER_API_URL + 'api/m-cut-actions';

  constructor(protected http: HttpClient) {}

  create(mCutAction: IMCutAction): Observable<EntityResponseType> {
    return this.http.post<IMCutAction>(this.resourceUrl, mCutAction, { observe: 'response' });
  }

  update(mCutAction: IMCutAction): Observable<EntityResponseType> {
    return this.http.put<IMCutAction>(this.resourceUrl, mCutAction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCutAction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCutAction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
