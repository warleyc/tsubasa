import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

type EntityResponseType = HttpResponse<IMLoginBonusRound>;
type EntityArrayResponseType = HttpResponse<IMLoginBonusRound[]>;

@Injectable({ providedIn: 'root' })
export class MLoginBonusRoundService {
  public resourceUrl = SERVER_API_URL + 'api/m-login-bonus-rounds';

  constructor(protected http: HttpClient) {}

  create(mLoginBonusRound: IMLoginBonusRound): Observable<EntityResponseType> {
    return this.http.post<IMLoginBonusRound>(this.resourceUrl, mLoginBonusRound, { observe: 'response' });
  }

  update(mLoginBonusRound: IMLoginBonusRound): Observable<EntityResponseType> {
    return this.http.put<IMLoginBonusRound>(this.resourceUrl, mLoginBonusRound, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLoginBonusRound>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLoginBonusRound[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
