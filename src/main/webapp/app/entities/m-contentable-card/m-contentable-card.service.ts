import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMContentableCard } from 'app/shared/model/m-contentable-card.model';

type EntityResponseType = HttpResponse<IMContentableCard>;
type EntityArrayResponseType = HttpResponse<IMContentableCard[]>;

@Injectable({ providedIn: 'root' })
export class MContentableCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-contentable-cards';

  constructor(protected http: HttpClient) {}

  create(mContentableCard: IMContentableCard): Observable<EntityResponseType> {
    return this.http.post<IMContentableCard>(this.resourceUrl, mContentableCard, { observe: 'response' });
  }

  update(mContentableCard: IMContentableCard): Observable<EntityResponseType> {
    return this.http.put<IMContentableCard>(this.resourceUrl, mContentableCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMContentableCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMContentableCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
