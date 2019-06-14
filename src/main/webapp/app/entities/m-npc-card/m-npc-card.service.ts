import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMNpcCard } from 'app/shared/model/m-npc-card.model';

type EntityResponseType = HttpResponse<IMNpcCard>;
type EntityArrayResponseType = HttpResponse<IMNpcCard[]>;

@Injectable({ providedIn: 'root' })
export class MNpcCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-npc-cards';

  constructor(protected http: HttpClient) {}

  create(mNpcCard: IMNpcCard): Observable<EntityResponseType> {
    return this.http.post<IMNpcCard>(this.resourceUrl, mNpcCard, { observe: 'response' });
  }

  update(mNpcCard: IMNpcCard): Observable<EntityResponseType> {
    return this.http.put<IMNpcCard>(this.resourceUrl, mNpcCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMNpcCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMNpcCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
