import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';

type EntityResponseType = HttpResponse<IMModelQuestStage>;
type EntityArrayResponseType = HttpResponse<IMModelQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MModelQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mModelQuestStage: IMModelQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMModelQuestStage>(this.resourceUrl, mModelQuestStage, { observe: 'response' });
  }

  update(mModelQuestStage: IMModelQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMModelQuestStage>(this.resourceUrl, mModelQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
