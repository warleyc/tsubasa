import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

type EntityResponseType = HttpResponse<IMGachaRenditionBeforeShootCutInPattern>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionBeforeShootCutInPattern[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionBeforeShootCutInPatternService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-before-shoot-cut-in-patterns';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionBeforeShootCutInPattern>(this.resourceUrl, mGachaRenditionBeforeShootCutInPattern, {
      observe: 'response'
    });
  }

  update(mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionBeforeShootCutInPattern>(this.resourceUrl, mGachaRenditionBeforeShootCutInPattern, {
      observe: 'response'
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionBeforeShootCutInPattern>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionBeforeShootCutInPattern[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
