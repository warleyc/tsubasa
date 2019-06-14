import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

type EntityResponseType = HttpResponse<IMInitUserDeck>;
type EntityArrayResponseType = HttpResponse<IMInitUserDeck[]>;

@Injectable({ providedIn: 'root' })
export class MInitUserDeckService {
  public resourceUrl = SERVER_API_URL + 'api/m-init-user-decks';

  constructor(protected http: HttpClient) {}

  create(mInitUserDeck: IMInitUserDeck): Observable<EntityResponseType> {
    return this.http.post<IMInitUserDeck>(this.resourceUrl, mInitUserDeck, { observe: 'response' });
  }

  update(mInitUserDeck: IMInitUserDeck): Observable<EntityResponseType> {
    return this.http.put<IMInitUserDeck>(this.resourceUrl, mInitUserDeck, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMInitUserDeck>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMInitUserDeck[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
