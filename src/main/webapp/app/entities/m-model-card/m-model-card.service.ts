import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelCard } from 'app/shared/model/m-model-card.model';

type EntityResponseType = HttpResponse<IMModelCard>;
type EntityArrayResponseType = HttpResponse<IMModelCard[]>;

@Injectable({ providedIn: 'root' })
export class MModelCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-cards';

  constructor(protected http: HttpClient) {}

  create(mModelCard: IMModelCard): Observable<EntityResponseType> {
    return this.http.post<IMModelCard>(this.resourceUrl, mModelCard, { observe: 'response' });
  }

  update(mModelCard: IMModelCard): Observable<EntityResponseType> {
    return this.http.put<IMModelCard>(this.resourceUrl, mModelCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
