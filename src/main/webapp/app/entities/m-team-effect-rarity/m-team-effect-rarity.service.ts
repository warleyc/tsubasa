import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';

type EntityResponseType = HttpResponse<IMTeamEffectRarity>;
type EntityArrayResponseType = HttpResponse<IMTeamEffectRarity[]>;

@Injectable({ providedIn: 'root' })
export class MTeamEffectRarityService {
  public resourceUrl = SERVER_API_URL + 'api/m-team-effect-rarities';

  constructor(protected http: HttpClient) {}

  create(mTeamEffectRarity: IMTeamEffectRarity): Observable<EntityResponseType> {
    return this.http.post<IMTeamEffectRarity>(this.resourceUrl, mTeamEffectRarity, { observe: 'response' });
  }

  update(mTeamEffectRarity: IMTeamEffectRarity): Observable<EntityResponseType> {
    return this.http.put<IMTeamEffectRarity>(this.resourceUrl, mTeamEffectRarity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTeamEffectRarity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTeamEffectRarity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
