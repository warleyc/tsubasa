import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';

type EntityResponseType = HttpResponse<IMRecommendShopMessage>;
type EntityArrayResponseType = HttpResponse<IMRecommendShopMessage[]>;

@Injectable({ providedIn: 'root' })
export class MRecommendShopMessageService {
  public resourceUrl = SERVER_API_URL + 'api/m-recommend-shop-messages';

  constructor(protected http: HttpClient) {}

  create(mRecommendShopMessage: IMRecommendShopMessage): Observable<EntityResponseType> {
    return this.http.post<IMRecommendShopMessage>(this.resourceUrl, mRecommendShopMessage, { observe: 'response' });
  }

  update(mRecommendShopMessage: IMRecommendShopMessage): Observable<EntityResponseType> {
    return this.http.put<IMRecommendShopMessage>(this.resourceUrl, mRecommendShopMessage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRecommendShopMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRecommendShopMessage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
