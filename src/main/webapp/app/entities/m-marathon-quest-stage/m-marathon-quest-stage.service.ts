import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

type EntityResponseType = HttpResponse<IMMarathonQuestStage>;
type EntityArrayResponseType = HttpResponse<IMMarathonQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mMarathonQuestStage: IMMarathonQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMMarathonQuestStage>(this.resourceUrl, mMarathonQuestStage, { observe: 'response' });
  }

  update(mMarathonQuestStage: IMMarathonQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMMarathonQuestStage>(this.resourceUrl, mMarathonQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
