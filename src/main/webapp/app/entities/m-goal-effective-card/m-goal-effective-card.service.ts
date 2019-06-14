import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';

type EntityResponseType = HttpResponse<IMGoalEffectiveCard>;
type EntityArrayResponseType = HttpResponse<IMGoalEffectiveCard[]>;

@Injectable({ providedIn: 'root' })
export class MGoalEffectiveCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-goal-effective-cards';

  constructor(protected http: HttpClient) {}

  create(mGoalEffectiveCard: IMGoalEffectiveCard): Observable<EntityResponseType> {
    return this.http.post<IMGoalEffectiveCard>(this.resourceUrl, mGoalEffectiveCard, { observe: 'response' });
  }

  update(mGoalEffectiveCard: IMGoalEffectiveCard): Observable<EntityResponseType> {
    return this.http.put<IMGoalEffectiveCard>(this.resourceUrl, mGoalEffectiveCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGoalEffectiveCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGoalEffectiveCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
