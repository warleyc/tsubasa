import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMAdventQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMAdventQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MAdventQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-advent-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mAdventQuestTsubasaPointReward: IMAdventQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMAdventQuestTsubasaPointReward>(this.resourceUrl, mAdventQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mAdventQuestTsubasaPointReward: IMAdventQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMAdventQuestTsubasaPointReward>(this.resourceUrl, mAdventQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAdventQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAdventQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
