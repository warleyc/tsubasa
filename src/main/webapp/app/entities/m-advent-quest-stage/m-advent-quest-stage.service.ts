import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

type EntityResponseType = HttpResponse<IMAdventQuestStage>;
type EntityArrayResponseType = HttpResponse<IMAdventQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MAdventQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-advent-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mAdventQuestStage: IMAdventQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMAdventQuestStage>(this.resourceUrl, mAdventQuestStage, { observe: 'response' });
  }

  update(mAdventQuestStage: IMAdventQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMAdventQuestStage>(this.resourceUrl, mAdventQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAdventQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAdventQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
