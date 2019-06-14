import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMNgWord } from 'app/shared/model/m-ng-word.model';

type EntityResponseType = HttpResponse<IMNgWord>;
type EntityArrayResponseType = HttpResponse<IMNgWord[]>;

@Injectable({ providedIn: 'root' })
export class MNgWordService {
  public resourceUrl = SERVER_API_URL + 'api/m-ng-words';

  constructor(protected http: HttpClient) {}

  create(mNgWord: IMNgWord): Observable<EntityResponseType> {
    return this.http.post<IMNgWord>(this.resourceUrl, mNgWord, { observe: 'response' });
  }

  update(mNgWord: IMNgWord): Observable<EntityResponseType> {
    return this.http.put<IMNgWord>(this.resourceUrl, mNgWord, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMNgWord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMNgWord[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
