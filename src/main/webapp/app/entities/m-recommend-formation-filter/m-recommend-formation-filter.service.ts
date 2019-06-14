import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';

type EntityResponseType = HttpResponse<IMRecommendFormationFilter>;
type EntityArrayResponseType = HttpResponse<IMRecommendFormationFilter[]>;

@Injectable({ providedIn: 'root' })
export class MRecommendFormationFilterService {
  public resourceUrl = SERVER_API_URL + 'api/m-recommend-formation-filters';

  constructor(protected http: HttpClient) {}

  create(mRecommendFormationFilter: IMRecommendFormationFilter): Observable<EntityResponseType> {
    return this.http.post<IMRecommendFormationFilter>(this.resourceUrl, mRecommendFormationFilter, { observe: 'response' });
  }

  update(mRecommendFormationFilter: IMRecommendFormationFilter): Observable<EntityResponseType> {
    return this.http.put<IMRecommendFormationFilter>(this.resourceUrl, mRecommendFormationFilter, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRecommendFormationFilter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRecommendFormationFilter[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
