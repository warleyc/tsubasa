import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestTicket } from 'app/shared/model/m-quest-ticket.model';

type EntityResponseType = HttpResponse<IMQuestTicket>;
type EntityArrayResponseType = HttpResponse<IMQuestTicket[]>;

@Injectable({ providedIn: 'root' })
export class MQuestTicketService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-tickets';

  constructor(protected http: HttpClient) {}

  create(mQuestTicket: IMQuestTicket): Observable<EntityResponseType> {
    return this.http.post<IMQuestTicket>(this.resourceUrl, mQuestTicket, { observe: 'response' });
  }

  update(mQuestTicket: IMQuestTicket): Observable<EntityResponseType> {
    return this.http.put<IMQuestTicket>(this.resourceUrl, mQuestTicket, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestTicket>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestTicket[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
