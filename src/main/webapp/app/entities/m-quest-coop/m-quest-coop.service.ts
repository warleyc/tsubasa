import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestCoop } from 'app/shared/model/m-quest-coop.model';

type EntityResponseType = HttpResponse<IMQuestCoop>;
type EntityArrayResponseType = HttpResponse<IMQuestCoop[]>;

@Injectable({ providedIn: 'root' })
export class MQuestCoopService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-coops';

  constructor(protected http: HttpClient) {}

  create(mQuestCoop: IMQuestCoop): Observable<EntityResponseType> {
    return this.http.post<IMQuestCoop>(this.resourceUrl, mQuestCoop, { observe: 'response' });
  }

  update(mQuestCoop: IMQuestCoop): Observable<EntityResponseType> {
    return this.http.put<IMQuestCoop>(this.resourceUrl, mQuestCoop, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestCoop>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestCoop[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
