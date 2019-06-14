import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

type EntityResponseType = HttpResponse<IMLuckWeeklyQuestStage>;
type EntityArrayResponseType = HttpResponse<IMLuckWeeklyQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-luck-weekly-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMLuckWeeklyQuestStage>(this.resourceUrl, mLuckWeeklyQuestStage, { observe: 'response' });
  }

  update(mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMLuckWeeklyQuestStage>(this.resourceUrl, mLuckWeeklyQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLuckWeeklyQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLuckWeeklyQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
