import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';

type EntityResponseType = HttpResponse<IMLoginBonusSerif>;
type EntityArrayResponseType = HttpResponse<IMLoginBonusSerif[]>;

@Injectable({ providedIn: 'root' })
export class MLoginBonusSerifService {
  public resourceUrl = SERVER_API_URL + 'api/m-login-bonus-serifs';

  constructor(protected http: HttpClient) {}

  create(mLoginBonusSerif: IMLoginBonusSerif): Observable<EntityResponseType> {
    return this.http.post<IMLoginBonusSerif>(this.resourceUrl, mLoginBonusSerif, { observe: 'response' });
  }

  update(mLoginBonusSerif: IMLoginBonusSerif): Observable<EntityResponseType> {
    return this.http.put<IMLoginBonusSerif>(this.resourceUrl, mLoginBonusSerif, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLoginBonusSerif>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLoginBonusSerif[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
