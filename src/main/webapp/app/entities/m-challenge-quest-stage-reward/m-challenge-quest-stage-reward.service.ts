import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMChallengeQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMChallengeQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MChallengeQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-challenge-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mChallengeQuestStageReward: IMChallengeQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMChallengeQuestStageReward>(this.resourceUrl, mChallengeQuestStageReward, { observe: 'response' });
  }

  update(mChallengeQuestStageReward: IMChallengeQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMChallengeQuestStageReward>(this.resourceUrl, mChallengeQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChallengeQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChallengeQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
