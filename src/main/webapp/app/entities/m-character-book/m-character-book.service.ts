import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCharacterBook } from 'app/shared/model/m-character-book.model';

type EntityResponseType = HttpResponse<IMCharacterBook>;
type EntityArrayResponseType = HttpResponse<IMCharacterBook[]>;

@Injectable({ providedIn: 'root' })
export class MCharacterBookService {
  public resourceUrl = SERVER_API_URL + 'api/m-character-books';

  constructor(protected http: HttpClient) {}

  create(mCharacterBook: IMCharacterBook): Observable<EntityResponseType> {
    return this.http.post<IMCharacterBook>(this.resourceUrl, mCharacterBook, { observe: 'response' });
  }

  update(mCharacterBook: IMCharacterBook): Observable<EntityResponseType> {
    return this.http.put<IMCharacterBook>(this.resourceUrl, mCharacterBook, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCharacterBook>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCharacterBook[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
