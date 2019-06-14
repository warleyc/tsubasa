import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';

type EntityResponseType = HttpResponse<IMGuildTopBanner>;
type EntityArrayResponseType = HttpResponse<IMGuildTopBanner[]>;

@Injectable({ providedIn: 'root' })
export class MGuildTopBannerService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-top-banners';

  constructor(protected http: HttpClient) {}

  create(mGuildTopBanner: IMGuildTopBanner): Observable<EntityResponseType> {
    return this.http.post<IMGuildTopBanner>(this.resourceUrl, mGuildTopBanner, { observe: 'response' });
  }

  update(mGuildTopBanner: IMGuildTopBanner): Observable<EntityResponseType> {
    return this.http.put<IMGuildTopBanner>(this.resourceUrl, mGuildTopBanner, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildTopBanner>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildTopBanner[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
