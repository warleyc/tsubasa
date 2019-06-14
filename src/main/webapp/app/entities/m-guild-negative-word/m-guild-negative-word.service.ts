import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';

type EntityResponseType = HttpResponse<IMGuildNegativeWord>;
type EntityArrayResponseType = HttpResponse<IMGuildNegativeWord[]>;

@Injectable({ providedIn: 'root' })
export class MGuildNegativeWordService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-negative-words';

  constructor(protected http: HttpClient) {}

  create(mGuildNegativeWord: IMGuildNegativeWord): Observable<EntityResponseType> {
    return this.http.post<IMGuildNegativeWord>(this.resourceUrl, mGuildNegativeWord, { observe: 'response' });
  }

  update(mGuildNegativeWord: IMGuildNegativeWord): Observable<EntityResponseType> {
    return this.http.put<IMGuildNegativeWord>(this.resourceUrl, mGuildNegativeWord, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildNegativeWord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildNegativeWord[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
