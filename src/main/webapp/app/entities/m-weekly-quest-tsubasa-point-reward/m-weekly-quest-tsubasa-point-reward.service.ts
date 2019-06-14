import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMWeeklyQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMWeeklyQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-weekly-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMWeeklyQuestTsubasaPointReward>(this.resourceUrl, mWeeklyQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMWeeklyQuestTsubasaPointReward>(this.resourceUrl, mWeeklyQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMWeeklyQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMWeeklyQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
