import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';

type EntityResponseType = HttpResponse<IMTicketQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMTicketQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MTicketQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-ticket-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mTicketQuestWorld: IMTicketQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMTicketQuestWorld>(this.resourceUrl, mTicketQuestWorld, { observe: 'response' });
  }

  update(mTicketQuestWorld: IMTicketQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMTicketQuestWorld>(this.resourceUrl, mTicketQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTicketQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTicketQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
