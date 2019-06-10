import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

type EntityResponseType = HttpResponse<IMChallengeQuestStage>;
type EntityArrayResponseType = HttpResponse<IMChallengeQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MChallengeQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-challenge-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mChallengeQuestStage: IMChallengeQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMChallengeQuestStage>(this.resourceUrl, mChallengeQuestStage, { observe: 'response' });
  }

  update(mChallengeQuestStage: IMChallengeQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMChallengeQuestStage>(this.resourceUrl, mChallengeQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChallengeQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChallengeQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
