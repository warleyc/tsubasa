import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTrainingCard } from 'app/shared/model/m-training-card.model';

type EntityResponseType = HttpResponse<IMTrainingCard>;
type EntityArrayResponseType = HttpResponse<IMTrainingCard[]>;

@Injectable({ providedIn: 'root' })
export class MTrainingCardService {
  public resourceUrl = SERVER_API_URL + 'api/m-training-cards';

  constructor(protected http: HttpClient) {}

  create(mTrainingCard: IMTrainingCard): Observable<EntityResponseType> {
    return this.http.post<IMTrainingCard>(this.resourceUrl, mTrainingCard, { observe: 'response' });
  }

  update(mTrainingCard: IMTrainingCard): Observable<EntityResponseType> {
    return this.http.put<IMTrainingCard>(this.resourceUrl, mTrainingCard, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTrainingCard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTrainingCard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
