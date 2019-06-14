import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMMarathonQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMMarathonQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMMarathonQuestTsubasaPointReward>(this.resourceUrl, mMarathonQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMMarathonQuestTsubasaPointReward>(this.resourceUrl, mMarathonQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
