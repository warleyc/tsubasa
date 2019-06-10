import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMAdventQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMAdventQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MAdventQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-advent-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mAdventQuestStageReward: IMAdventQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMAdventQuestStageReward>(this.resourceUrl, mAdventQuestStageReward, { observe: 'response' });
  }

  update(mAdventQuestStageReward: IMAdventQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMAdventQuestStageReward>(this.resourceUrl, mAdventQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAdventQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAdventQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
