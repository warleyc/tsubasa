import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestStage } from 'app/shared/model/m-quest-stage.model';

type EntityResponseType = HttpResponse<IMQuestStage>;
type EntityArrayResponseType = HttpResponse<IMQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mQuestStage: IMQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMQuestStage>(this.resourceUrl, mQuestStage, { observe: 'response' });
  }

  update(mQuestStage: IMQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMQuestStage>(this.resourceUrl, mQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
