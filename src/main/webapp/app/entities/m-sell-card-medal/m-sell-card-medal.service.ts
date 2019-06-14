import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';

type EntityResponseType = HttpResponse<IMSellCardMedal>;
type EntityArrayResponseType = HttpResponse<IMSellCardMedal[]>;

@Injectable({ providedIn: 'root' })
export class MSellCardMedalService {
  public resourceUrl = SERVER_API_URL + 'api/m-sell-card-medals';

  constructor(protected http: HttpClient) {}

  create(mSellCardMedal: IMSellCardMedal): Observable<EntityResponseType> {
    return this.http.post<IMSellCardMedal>(this.resourceUrl, mSellCardMedal, { observe: 'response' });
  }

  update(mSellCardMedal: IMSellCardMedal): Observable<EntityResponseType> {
    return this.http.put<IMSellCardMedal>(this.resourceUrl, mSellCardMedal, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSellCardMedal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSellCardMedal[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
