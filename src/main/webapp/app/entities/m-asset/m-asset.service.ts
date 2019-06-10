import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAsset } from 'app/shared/model/m-asset.model';

type EntityResponseType = HttpResponse<IMAsset>;
type EntityArrayResponseType = HttpResponse<IMAsset[]>;

@Injectable({ providedIn: 'root' })
export class MAssetService {
  public resourceUrl = SERVER_API_URL + 'api/m-assets';

  constructor(protected http: HttpClient) {}

  create(mAsset: IMAsset): Observable<EntityResponseType> {
    return this.http.post<IMAsset>(this.resourceUrl, mAsset, { observe: 'response' });
  }

  update(mAsset: IMAsset): Observable<EntityResponseType> {
    return this.http.put<IMAsset>(this.resourceUrl, mAsset, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAsset>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAsset[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
