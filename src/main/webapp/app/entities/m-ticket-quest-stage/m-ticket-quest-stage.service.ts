import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

type EntityResponseType = HttpResponse<IMTicketQuestStage>;
type EntityArrayResponseType = HttpResponse<IMTicketQuestStage[]>;

@Injectable({ providedIn: 'root' })
export class MTicketQuestStageService {
  public resourceUrl = SERVER_API_URL + 'api/m-ticket-quest-stages';

  constructor(protected http: HttpClient) {}

  create(mTicketQuestStage: IMTicketQuestStage): Observable<EntityResponseType> {
    return this.http.post<IMTicketQuestStage>(this.resourceUrl, mTicketQuestStage, { observe: 'response' });
  }

  update(mTicketQuestStage: IMTicketQuestStage): Observable<EntityResponseType> {
    return this.http.put<IMTicketQuestStage>(this.resourceUrl, mTicketQuestStage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTicketQuestStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTicketQuestStage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
