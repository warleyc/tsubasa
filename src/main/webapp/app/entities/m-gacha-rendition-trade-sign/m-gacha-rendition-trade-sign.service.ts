import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';

type EntityResponseType = HttpResponse<IMGachaRenditionTradeSign>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionTradeSign[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTradeSignService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-trade-signs';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionTradeSign: IMGachaRenditionTradeSign): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionTradeSign>(this.resourceUrl, mGachaRenditionTradeSign, { observe: 'response' });
  }

  update(mGachaRenditionTradeSign: IMGachaRenditionTradeSign): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionTradeSign>(this.resourceUrl, mGachaRenditionTradeSign, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionTradeSign>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionTradeSign[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
