import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMGuerillaQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMGuerillaQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-guerilla-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMGuerillaQuestTsubasaPointReward>(this.resourceUrl, mGuerillaQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMGuerillaQuestTsubasaPointReward>(this.resourceUrl, mGuerillaQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuerillaQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuerillaQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
