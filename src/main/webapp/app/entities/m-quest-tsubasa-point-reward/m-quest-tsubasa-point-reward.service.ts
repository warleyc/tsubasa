import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mQuestTsubasaPointReward: IMQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMQuestTsubasaPointReward>(this.resourceUrl, mQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mQuestTsubasaPointReward: IMQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMQuestTsubasaPointReward>(this.resourceUrl, mQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
