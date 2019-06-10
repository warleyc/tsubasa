import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';

type EntityResponseType = HttpResponse<IMGachaRenditionSwipeIcon>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionSwipeIcon[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionSwipeIconService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-swipe-icons';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionSwipeIcon>(this.resourceUrl, mGachaRenditionSwipeIcon, { observe: 'response' });
  }

  update(mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionSwipeIcon>(this.resourceUrl, mGachaRenditionSwipeIcon, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionSwipeIcon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionSwipeIcon[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
