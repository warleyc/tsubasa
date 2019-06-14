import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';

type EntityResponseType = HttpResponse<IMTimeattackQuestStage>;
type EntityArrayResponseType = HttpResponse<IMTimeattackQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-timeattack-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mTimeattackQuestStage: IMTimeattackQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMTimeattackQuestStage>(this.resourceUrl, mTimeattackQuestStage, { observe: 'response' });
  }

  update(mTimeattackQuestStage: IMTimeattackQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMTimeattackQuestStage>(this.resourceUrl, mTimeattackQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTimeattackQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTimeattackQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
