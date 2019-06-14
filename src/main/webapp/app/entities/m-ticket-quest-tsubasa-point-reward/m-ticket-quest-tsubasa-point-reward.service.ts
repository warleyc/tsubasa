import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';

type EntityResponseType = HttpResponse<IMTicketQuestTsubasaPointReward>;
type EntityArrayResponseType = HttpResponse<IMTicketQuestTsubasaPointReward[]>;

@Injectable({ providedIn: 'root' })
export class MTicketQuestTsubasaPointRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-ticket-quest-tsubasa-point-rewards';

  constructor(protected http: HttpClient) {}

  create(mTicketQuestTsubasaPointReward: IMTicketQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.post<IMTicketQuestTsubasaPointReward>(this.resourceUrl, mTicketQuestTsubasaPointReward, { observe: 'response' });
  }

  update(mTicketQuestTsubasaPointReward: IMTicketQuestTsubasaPointReward): Observable<EntityResponseType> {
    return this.http.put<IMTicketQuestTsubasaPointReward>(this.resourceUrl, mTicketQuestTsubasaPointReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTicketQuestTsubasaPointReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTicketQuestTsubasaPointReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
