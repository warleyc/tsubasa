import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';

type EntityResponseType = HttpResponse<IMChallengeQuestAchievementRewardGroup>;
type EntityArrayResponseType = HttpResponse<IMChallengeQuestAchievementRewardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MChallengeQuestAchievementRewardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-challenge-quest-achievement-reward-groups';

  constructor(protected http: HttpClient) {}

  create(mChallengeQuestAchievementRewardGroup: IMChallengeQuestAchievementRewardGroup): Observable<EntityResponseType> {
    return this.http.post<IMChallengeQuestAchievementRewardGroup>(this.resourceUrl, mChallengeQuestAchievementRewardGroup, {
      observe: 'response'
    });
  }

  update(mChallengeQuestAchievementRewardGroup: IMChallengeQuestAchievementRewardGroup): Observable<EntityResponseType> {
    return this.http.put<IMChallengeQuestAchievementRewardGroup>(this.resourceUrl, mChallengeQuestAchievementRewardGroup, {
      observe: 'response'
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChallengeQuestAchievementRewardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChallengeQuestAchievementRewardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
