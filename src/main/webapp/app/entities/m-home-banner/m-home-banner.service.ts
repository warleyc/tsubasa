import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMHomeBanner } from 'app/shared/model/m-home-banner.model';

type EntityResponseType = HttpResponse<IMHomeBanner>;
type EntityArrayResponseType = HttpResponse<IMHomeBanner[]>;

@Injectable({ providedIn: 'root' })
export class MHomeBannerService {
  public resourceUrl = SERVER_API_URL + 'api/m-home-banners';

  constructor(protected http: HttpClient) {}

  create(mHomeBanner: IMHomeBanner): Observable<EntityResponseType> {
    return this.http.post<IMHomeBanner>(this.resourceUrl, mHomeBanner, { observe: 'response' });
  }

  update(mHomeBanner: IMHomeBanner): Observable<EntityResponseType> {
    return this.http.put<IMHomeBanner>(this.resourceUrl, mHomeBanner, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMHomeBanner>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMHomeBanner[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
