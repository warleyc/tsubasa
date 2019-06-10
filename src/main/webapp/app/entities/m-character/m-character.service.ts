import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCharacter } from 'app/shared/model/m-character.model';

type EntityResponseType = HttpResponse<IMCharacter>;
type EntityArrayResponseType = HttpResponse<IMCharacter[]>;

@Injectable({ providedIn: 'root' })
export class MCharacterService {
  public resourceUrl = SERVER_API_URL + 'api/m-characters';

  constructor(protected http: HttpClient) {}

  create(mCharacter: IMCharacter): Observable<EntityResponseType> {
    return this.http.post<IMCharacter>(this.resourceUrl, mCharacter, { observe: 'response' });
  }

  update(mCharacter: IMCharacter): Observable<EntityResponseType> {
    return this.http.put<IMCharacter>(this.resourceUrl, mCharacter, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCharacter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCharacter[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
