import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';

type EntityResponseType = HttpResponse<IMUserPolicyUpdateDate>;
type EntityArrayResponseType = HttpResponse<IMUserPolicyUpdateDate[]>;

@Injectable({ providedIn: 'root' })
export class MUserPolicyUpdateDateService {
  public resourceUrl = SERVER_API_URL + 'api/m-user-policy-update-dates';

  constructor(protected http: HttpClient) {}

  create(mUserPolicyUpdateDate: IMUserPolicyUpdateDate): Observable<EntityResponseType> {
    return this.http.post<IMUserPolicyUpdateDate>(this.resourceUrl, mUserPolicyUpdateDate, { observe: 'response' });
  }

  update(mUserPolicyUpdateDate: IMUserPolicyUpdateDate): Observable<EntityResponseType> {
    return this.http.put<IMUserPolicyUpdateDate>(this.resourceUrl, mUserPolicyUpdateDate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMUserPolicyUpdateDate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMUserPolicyUpdateDate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
