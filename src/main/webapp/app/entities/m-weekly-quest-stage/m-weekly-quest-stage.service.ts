import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

type EntityResponseType = HttpResponse<IMWeeklyQuestStage>;
type EntityArrayResponseType = HttpResponse<IMWeeklyQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-weekly-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mWeeklyQuestStage: IMWeeklyQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMWeeklyQuestStage>(this.resourceUrl, mWeeklyQuestStage, { observe: 'response' });
  }

  update(mWeeklyQuestStage: IMWeeklyQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMWeeklyQuestStage>(this.resourceUrl, mWeeklyQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMWeeklyQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMWeeklyQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
