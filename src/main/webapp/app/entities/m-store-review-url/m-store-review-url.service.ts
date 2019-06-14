import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';

type EntityResponseType = HttpResponse<IMStoreReviewUrl>;
type EntityArrayResponseType = HttpResponse<IMStoreReviewUrl[]>;

@Injectable({ providedIn: 'root' })
export class MStoreReviewUrlService {
  public resourceUrl = SERVER_API_URL + 'api/m-store-review-urls';

  constructor(protected http: HttpClient) {}

  create(mStoreReviewUrl: IMStoreReviewUrl): Observable<EntityResponseType> {
    return this.http.post<IMStoreReviewUrl>(this.resourceUrl, mStoreReviewUrl, { observe: 'response' });
  }

  update(mStoreReviewUrl: IMStoreReviewUrl): Observable<EntityResponseType> {
    return this.http.put<IMStoreReviewUrl>(this.resourceUrl, mStoreReviewUrl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMStoreReviewUrl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMStoreReviewUrl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
