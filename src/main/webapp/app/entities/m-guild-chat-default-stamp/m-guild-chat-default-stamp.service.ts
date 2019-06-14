import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';

type EntityResponseType = HttpResponse<IMGuildChatDefaultStamp>;
type EntityArrayResponseType = HttpResponse<IMGuildChatDefaultStamp[]>;

@Injectable({ providedIn: 'root' })
export class MGuildChatDefaultStampService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-chat-default-stamps';

  constructor(protected http: HttpClient) {}

  create(mGuildChatDefaultStamp: IMGuildChatDefaultStamp): Observable<EntityResponseType> {
    return this.http.post<IMGuildChatDefaultStamp>(this.resourceUrl, mGuildChatDefaultStamp, { observe: 'response' });
  }

  update(mGuildChatDefaultStamp: IMGuildChatDefaultStamp): Observable<EntityResponseType> {
    return this.http.put<IMGuildChatDefaultStamp>(this.resourceUrl, mGuildChatDefaultStamp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildChatDefaultStamp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildChatDefaultStamp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
