import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';

type EntityResponseType = HttpResponse<IMLuckWeeklyQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMLuckWeeklyQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-luck-weekly-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMLuckWeeklyQuestWorld>(this.resourceUrl, mLuckWeeklyQuestWorld, { observe: 'response' });
  }

  update(mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMLuckWeeklyQuestWorld>(this.resourceUrl, mLuckWeeklyQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLuckWeeklyQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLuckWeeklyQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
