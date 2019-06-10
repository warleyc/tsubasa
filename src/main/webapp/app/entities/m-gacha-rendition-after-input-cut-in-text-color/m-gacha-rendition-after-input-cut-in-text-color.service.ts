import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

type EntityResponseType = HttpResponse<IMGachaRenditionAfterInputCutInTextColor>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionAfterInputCutInTextColor[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionAfterInputCutInTextColorService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-after-input-cut-in-text-colors';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionAfterInputCutInTextColor>(this.resourceUrl, mGachaRenditionAfterInputCutInTextColor, {
      observe: 'response'
    });
  }

  update(mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionAfterInputCutInTextColor>(this.resourceUrl, mGachaRenditionAfterInputCutInTextColor, {
      observe: 'response'
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionAfterInputCutInTextColor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionAfterInputCutInTextColor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
