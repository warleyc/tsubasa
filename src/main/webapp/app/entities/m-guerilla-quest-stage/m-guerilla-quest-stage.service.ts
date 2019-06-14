import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

type EntityResponseType = HttpResponse<IMGuerillaQuestStage>;
type EntityArrayResponseType = HttpResponse<IMGuerillaQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-guerilla-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mGuerillaQuestStage: IMGuerillaQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMGuerillaQuestStage>(this.resourceUrl, mGuerillaQuestStage, { observe: 'response' });
  }

  update(mGuerillaQuestStage: IMGuerillaQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMGuerillaQuestStage>(this.resourceUrl, mGuerillaQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuerillaQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuerillaQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
