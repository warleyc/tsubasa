import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';

type EntityResponseType = HttpResponse<IMRecommendFormationFilterElement>;
type EntityArrayResponseType = HttpResponse<IMRecommendFormationFilterElement[]>;

@Injectable({ providedIn: 'root' })
export class MRecommendFormationFilterElementService {
  public resourceUrl = SERVER_API_URL + 'api/m-recommend-formation-filter-elements';

  constructor(protected http: HttpClient) {}

  create(mRecommendFormationFilterElement: IMRecommendFormationFilterElement): Observable<EntityResponseType> {
    return this.http.post<IMRecommendFormationFilterElement>(this.resourceUrl, mRecommendFormationFilterElement, { observe: 'response' });
  }

  update(mRecommendFormationFilterElement: IMRecommendFormationFilterElement): Observable<EntityResponseType> {
    return this.http.put<IMRecommendFormationFilterElement>(this.resourceUrl, mRecommendFormationFilterElement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRecommendFormationFilterElement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRecommendFormationFilterElement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
