import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';

type EntityResponseType = HttpResponse<IMQuestStageConditionDescription>;
type EntityArrayResponseType = HttpResponse<IMQuestStageConditionDescription[]>;

@Injectable({ providedIn: 'root' })
export class MQuestStageConditionDescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-stage-condition-descriptions';

  constructor(protected http: HttpClient) {}

  create(mQuestStageConditionDescription: IMQuestStageConditionDescription): Observable<EntityResponseType> {
    return this.http.post<IMQuestStageConditionDescription>(this.resourceUrl, mQuestStageConditionDescription, { observe: 'response' });
  }

  update(mQuestStageConditionDescription: IMQuestStageConditionDescription): Observable<EntityResponseType> {
    return this.http.put<IMQuestStageConditionDescription>(this.resourceUrl, mQuestStageConditionDescription, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestStageConditionDescription>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestStageConditionDescription[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
