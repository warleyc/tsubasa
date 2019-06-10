import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';

type EntityResponseType = HttpResponse<IMCharacterScoreCut>;
type EntityArrayResponseType = HttpResponse<IMCharacterScoreCut[]>;

@Injectable({ providedIn: 'root' })
export class MCharacterScoreCutService {
  public resourceUrl = SERVER_API_URL + 'api/m-character-score-cuts';

  constructor(protected http: HttpClient) {}

  create(mCharacterScoreCut: IMCharacterScoreCut): Observable<EntityResponseType> {
    return this.http.post<IMCharacterScoreCut>(this.resourceUrl, mCharacterScoreCut, { observe: 'response' });
  }

  update(mCharacterScoreCut: IMCharacterScoreCut): Observable<EntityResponseType> {
    return this.http.put<IMCharacterScoreCut>(this.resourceUrl, mCharacterScoreCut, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCharacterScoreCut>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCharacterScoreCut[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
