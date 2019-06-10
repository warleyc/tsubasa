import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEmblemParts } from 'app/shared/model/m-emblem-parts.model';

type EntityResponseType = HttpResponse<IMEmblemParts>;
type EntityArrayResponseType = HttpResponse<IMEmblemParts[]>;

@Injectable({ providedIn: 'root' })
export class MEmblemPartsService {
  public resourceUrl = SERVER_API_URL + 'api/m-emblem-parts';

  constructor(protected http: HttpClient) {}

  create(mEmblemParts: IMEmblemParts): Observable<EntityResponseType> {
    return this.http.post<IMEmblemParts>(this.resourceUrl, mEmblemParts, { observe: 'response' });
  }

  update(mEmblemParts: IMEmblemParts): Observable<EntityResponseType> {
    return this.http.put<IMEmblemParts>(this.resourceUrl, mEmblemParts, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEmblemParts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEmblemParts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
