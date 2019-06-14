import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';

type EntityResponseType = HttpResponse<IMSellCardCoin>;
type EntityArrayResponseType = HttpResponse<IMSellCardCoin[]>;

@Injectable({ providedIn: 'root' })
export class MSellCardCoinService {
  public resourceUrl = SERVER_API_URL + 'api/m-sell-card-coins';

  constructor(protected http: HttpClient) {}

  create(mSellCardCoin: IMSellCardCoin): Observable<EntityResponseType> {
    return this.http.post<IMSellCardCoin>(this.resourceUrl, mSellCardCoin, { observe: 'response' });
  }

  update(mSellCardCoin: IMSellCardCoin): Observable<EntityResponseType> {
    return this.http.put<IMSellCardCoin>(this.resourceUrl, mSellCardCoin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSellCardCoin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSellCardCoin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
