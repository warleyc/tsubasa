import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';

type EntityResponseType = HttpResponse<IMQuestStageCondition>;
type EntityArrayResponseType = HttpResponse<IMQuestStageCondition[]>;

@Injectable({ providedIn: 'root' })
export class MQuestStageConditionService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-stage-conditions';

  constructor(protected http: HttpClient) {}

  create(mQuestStageCondition: IMQuestStageCondition): Observable<EntityResponseType> {
    return this.http.post<IMQuestStageCondition>(this.resourceUrl, mQuestStageCondition, { observe: 'response' });
  }

  update(mQuestStageCondition: IMQuestStageCondition): Observable<EntityResponseType> {
    return this.http.put<IMQuestStageCondition>(this.resourceUrl, mQuestStageCondition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestStageCondition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestStageCondition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
