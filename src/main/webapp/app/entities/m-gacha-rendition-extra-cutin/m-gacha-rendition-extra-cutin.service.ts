import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

type EntityResponseType = HttpResponse<IMGachaRenditionExtraCutin>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionExtraCutin[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionExtraCutinService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-extra-cutins';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionExtraCutin>(this.resourceUrl, mGachaRenditionExtraCutin, { observe: 'response' });
  }

  update(mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionExtraCutin>(this.resourceUrl, mGachaRenditionExtraCutin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionExtraCutin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionExtraCutin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
