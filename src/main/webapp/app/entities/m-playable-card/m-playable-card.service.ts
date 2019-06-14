import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';

type EntityResponseType = HttpResponse<IMPlayableCard>;
type EntityArrayResponseType = HttpResponse<IMPlayableCard[]>;

@Injectable({ providedIn: 'root' })
export class MPlayableCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-playable-cards';

  constructor(protected http: HttpClient) {}

  create(mPlayableCard: IMPlayableCard): Observable<EntityResponseType> {
    return this.http.post<IMPlayableCard>(this.resourceUrl, mPlayableCard, { observe: 'response' });
  }

  update(mPlayableCard: IMPlayableCard): Observable<EntityResponseType> {
    return this.http.put<IMPlayableCard>(this.resourceUrl, mPlayableCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPlayableCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPlayableCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
