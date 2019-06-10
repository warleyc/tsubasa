import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRendition } from 'app/shared/model/m-gacha-rendition.model';

type EntityResponseType = HttpResponse<IMGachaRendition>;
type EntityArrayResponseType = HttpResponse<IMGachaRendition[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-renditions';

  constructor(protected http: HttpClient) {}

  create(mGachaRendition: IMGachaRendition): Observable<EntityResponseType> {
    return this.http.post<IMGachaRendition>(this.resourceUrl, mGachaRendition, { observe: 'response' });
  }

  update(mGachaRendition: IMGachaRendition): Observable<EntityResponseType> {
    return this.http.put<IMGachaRendition>(this.resourceUrl, mGachaRendition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRendition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRendition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
