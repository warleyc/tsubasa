import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';

type EntityResponseType = HttpResponse<IMGachaRenditionAfterInputCutIn>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionAfterInputCutIn[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionAfterInputCutInService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-after-input-cut-ins';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionAfterInputCutIn>(this.resourceUrl, mGachaRenditionAfterInputCutIn, { observe: 'response' });
  }

  update(mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionAfterInputCutIn>(this.resourceUrl, mGachaRenditionAfterInputCutIn, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionAfterInputCutIn>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionAfterInputCutIn[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
