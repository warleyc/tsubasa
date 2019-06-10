import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMActionRarity } from 'app/shared/model/m-action-rarity.model';

type EntityResponseType = HttpResponse<IMActionRarity>;
type EntityArrayResponseType = HttpResponse<IMActionRarity[]>;

@Injectable({ providedIn: 'root' })
export class MActionRarityService {
  public resourceUrl = SERVER_API_URL + 'api/m-action-rarities';

  constructor(protected http: HttpClient) {}

  create(mActionRarity: IMActionRarity): Observable<EntityResponseType> {
    return this.http.post<IMActionRarity>(this.resourceUrl, mActionRarity, { observe: 'response' });
  }

  update(mActionRarity: IMActionRarity): Observable<EntityResponseType> {
    return this.http.put<IMActionRarity>(this.resourceUrl, mActionRarity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMActionRarity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMActionRarity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
