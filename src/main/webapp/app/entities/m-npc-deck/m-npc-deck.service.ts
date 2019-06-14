import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';

type EntityResponseType = HttpResponse<IMNpcDeck>;
type EntityArrayResponseType = HttpResponse<IMNpcDeck[]>;

@Injectable({ providedIn: 'root' })
export class MNpcDeckService {
  public resourceUrl = SERVER_API_URL + 'api/m-npc-decks';

  constructor(protected http: HttpClient) {}

  create(mNpcDeck: IMNpcDeck): Observable<EntityResponseType> {
    return this.http.post<IMNpcDeck>(this.resourceUrl, mNpcDeck, { observe: 'response' });
  }

  update(mNpcDeck: IMNpcDeck): Observable<EntityResponseType> {
    return this.http.put<IMNpcDeck>(this.resourceUrl, mNpcDeck, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMNpcDeck>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMNpcDeck[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
