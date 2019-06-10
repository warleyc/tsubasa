import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';

type EntityResponseType = HttpResponse<IMAssetTagMapping>;
type EntityArrayResponseType = HttpResponse<IMAssetTagMapping[]>;

@Injectable({ providedIn: 'root' })
export class MAssetTagMappingService {
  public resourceUrl = SERVER_API_URL + 'api/m-asset-tag-mappings';

  constructor(protected http: HttpClient) {}

  create(mAssetTagMapping: IMAssetTagMapping): Observable<EntityResponseType> {
    return this.http.post<IMAssetTagMapping>(this.resourceUrl, mAssetTagMapping, { observe: 'response' });
  }

  update(mAssetTagMapping: IMAssetTagMapping): Observable<EntityResponseType> {
    return this.http.put<IMAssetTagMapping>(this.resourceUrl, mAssetTagMapping, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAssetTagMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAssetTagMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
