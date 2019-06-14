import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMMarathonQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMMarathonQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mMarathonQuestStageReward: IMMarathonQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMMarathonQuestStageReward>(this.resourceUrl, mMarathonQuestStageReward, { observe: 'response' });
  }

  update(mMarathonQuestStageReward: IMMarathonQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMMarathonQuestStageReward>(this.resourceUrl, mMarathonQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
