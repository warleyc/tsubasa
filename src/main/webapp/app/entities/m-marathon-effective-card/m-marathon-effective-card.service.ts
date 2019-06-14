import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';

type EntityResponseType = HttpResponse<IMMarathonEffectiveCard>;
type EntityArrayResponseType = HttpResponse<IMMarathonEffectiveCard[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonEffectiveCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-effective-cards';

  constructor(protected http: HttpClient) {}

  create(mMarathonEffectiveCard: IMMarathonEffectiveCard): Observable<EntityResponseType> {
    return this.http.post<IMMarathonEffectiveCard>(this.resourceUrl, mMarathonEffectiveCard, { observe: 'response' });
  }

  update(mMarathonEffectiveCard: IMMarathonEffectiveCard): Observable<EntityResponseType> {
    return this.http.put<IMMarathonEffectiveCard>(this.resourceUrl, mMarathonEffectiveCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonEffectiveCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonEffectiveCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
