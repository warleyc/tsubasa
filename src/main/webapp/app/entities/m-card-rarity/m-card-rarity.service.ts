import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardRarity } from 'app/shared/model/m-card-rarity.model';

type EntityResponseType = HttpResponse<IMCardRarity>;
type EntityArrayResponseType = HttpResponse<IMCardRarity[]>;

@Injectable({ providedIn: 'root' })
export class MCardRarityService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-rarities';

  constructor(protected http: HttpClient) {}

  create(mCardRarity: IMCardRarity): Observable<EntityResponseType> {
    return this.http.post<IMCardRarity>(this.resourceUrl, mCardRarity, { observe: 'response' });
  }

  update(mCardRarity: IMCardRarity): Observable<EntityResponseType> {
    return this.http.put<IMCardRarity>(this.resourceUrl, mCardRarity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardRarity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardRarity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
