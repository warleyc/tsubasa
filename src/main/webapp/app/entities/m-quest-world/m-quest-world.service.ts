import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestWorld } from 'app/shared/model/m-quest-world.model';

type EntityResponseType = HttpResponse<IMQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mQuestWorld: IMQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMQuestWorld>(this.resourceUrl, mQuestWorld, { observe: 'response' });
  }

  update(mQuestWorld: IMQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMQuestWorld>(this.resourceUrl, mQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
