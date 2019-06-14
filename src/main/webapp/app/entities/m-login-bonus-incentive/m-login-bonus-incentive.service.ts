import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

type EntityResponseType = HttpResponse<IMLoginBonusIncentive>;
type EntityArrayResponseType = HttpResponse<IMLoginBonusIncentive[]>;

@Injectable({ providedIn: 'root' })
export class MLoginBonusIncentiveService {
  public resourceUrl = SERVER_API_URL + 'api/m-login-bonus-incentives';

  constructor(protected http: HttpClient) {}

  create(mLoginBonusIncentive: IMLoginBonusIncentive): Observable<EntityResponseType> {
    return this.http.post<IMLoginBonusIncentive>(this.resourceUrl, mLoginBonusIncentive, { observe: 'response' });
  }

  update(mLoginBonusIncentive: IMLoginBonusIncentive): Observable<EntityResponseType> {
    return this.http.put<IMLoginBonusIncentive>(this.resourceUrl, mLoginBonusIncentive, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLoginBonusIncentive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLoginBonusIncentive[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
