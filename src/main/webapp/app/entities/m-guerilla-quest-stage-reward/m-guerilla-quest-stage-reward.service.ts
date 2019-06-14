import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';

type EntityResponseType = HttpResponse<IMGuerillaQuestStageReward>;
type EntityArrayResponseType = HttpResponse<IMGuerillaQuestStageReward[]>;

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestStageRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-guerilla-quest-stage-rewards';

  constructor(protected http: HttpClient) {}

  create(mGuerillaQuestStageReward: IMGuerillaQuestStageReward): Observable<EntityResponseType> {
    return this.http.post<IMGuerillaQuestStageReward>(this.resourceUrl, mGuerillaQuestStageReward, { observe: 'response' });
  }

  update(mGuerillaQuestStageReward: IMGuerillaQuestStageReward): Observable<EntityResponseType> {
    return this.http.put<IMGuerillaQuestStageReward>(this.resourceUrl, mGuerillaQuestStageReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuerillaQuestStageReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuerillaQuestStageReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
