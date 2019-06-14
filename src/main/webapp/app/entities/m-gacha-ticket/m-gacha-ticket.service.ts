import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaTicket } from 'app/shared/model/m-gacha-ticket.model';

type EntityResponseType = HttpResponse<IMGachaTicket>;
type EntityArrayResponseType = HttpResponse<IMGachaTicket[]>;

@Injectable({ providedIn: 'root' })
export class MGachaTicketService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-tickets';

  constructor(protected http: HttpClient) {}

  create(mGachaTicket: IMGachaTicket): Observable<EntityResponseType> {
    return this.http.post<IMGachaTicket>(this.resourceUrl, mGachaTicket, { observe: 'response' });
  }

  update(mGachaTicket: IMGachaTicket): Observable<EntityResponseType> {
    return this.http.put<IMGachaTicket>(this.resourceUrl, mGachaTicket, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaTicket>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaTicket[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
