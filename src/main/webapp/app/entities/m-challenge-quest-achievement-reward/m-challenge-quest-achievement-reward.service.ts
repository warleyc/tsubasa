import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';

type EntityResponseType = HttpResponse<IMChallengeQuestAchievementReward>;
type EntityArrayResponseType = HttpResponse<IMChallengeQuestAchievementReward[]>;

@Injectable({ providedIn: 'root' })
export class MChallengeQuestAchievementRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-challenge-quest-achievement-rewards';

  constructor(protected http: HttpClient) {}

  create(mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward): Observable<EntityResponseType> {
    return this.http.post<IMChallengeQuestAchievementReward>(this.resourceUrl, mChallengeQuestAchievementReward, { observe: 'response' });
  }

  update(mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward): Observable<EntityResponseType> {
    return this.http.put<IMChallengeQuestAchievementReward>(this.resourceUrl, mChallengeQuestAchievementReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChallengeQuestAchievementReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChallengeQuestAchievementReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
