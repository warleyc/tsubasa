import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDeckCondition } from 'app/shared/model/m-deck-condition.model';

type EntityResponseType = HttpResponse<IMDeckCondition>;
type EntityArrayResponseType = HttpResponse<IMDeckCondition[]>;

@Injectable({ providedIn: 'root' })
export class MDeckConditionService {
  public resourceUrl = SERVER_API_URL + 'api/m-deck-conditions';

  constructor(protected http: HttpClient) {}

  create(mDeckCondition: IMDeckCondition): Observable<EntityResponseType> {
    return this.http.post<IMDeckCondition>(this.resourceUrl, mDeckCondition, { observe: 'response' });
  }

  update(mDeckCondition: IMDeckCondition): Observable<EntityResponseType> {
    return this.http.put<IMDeckCondition>(this.resourceUrl, mDeckCondition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDeckCondition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDeckCondition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
