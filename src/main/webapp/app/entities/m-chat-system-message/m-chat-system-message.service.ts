import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';

type EntityResponseType = HttpResponse<IMChatSystemMessage>;
type EntityArrayResponseType = HttpResponse<IMChatSystemMessage[]>;

@Injectable({ providedIn: 'root' })
export class MChatSystemMessageService {
  public resourceUrl = SERVER_API_URL + 'api/m-chat-system-messages';

  constructor(protected http: HttpClient) {}

  create(mChatSystemMessage: IMChatSystemMessage): Observable<EntityResponseType> {
    return this.http.post<IMChatSystemMessage>(this.resourceUrl, mChatSystemMessage, { observe: 'response' });
  }

  update(mChatSystemMessage: IMChatSystemMessage): Observable<EntityResponseType> {
    return this.http.put<IMChatSystemMessage>(this.resourceUrl, mChatSystemMessage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChatSystemMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChatSystemMessage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
