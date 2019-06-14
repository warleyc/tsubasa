import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';

type EntityResponseType = HttpResponse<IMWeeklyQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMWeeklyQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-weekly-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mWeeklyQuestWorld: IMWeeklyQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMWeeklyQuestWorld>(this.resourceUrl, mWeeklyQuestWorld, { observe: 'response' });
  }

  update(mWeeklyQuestWorld: IMWeeklyQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMWeeklyQuestWorld>(this.resourceUrl, mWeeklyQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMWeeklyQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMWeeklyQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
