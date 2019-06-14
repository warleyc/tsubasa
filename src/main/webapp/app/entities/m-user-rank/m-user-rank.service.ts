import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMUserRank } from 'app/shared/model/m-user-rank.model';

type EntityResponseType = HttpResponse<IMUserRank>;
type EntityArrayResponseType = HttpResponse<IMUserRank[]>;

@Injectable({ providedIn: 'root' })
export class MUserRankService {
  public resourceUrl = SERVER_API_URL + 'api/m-user-ranks';

  constructor(protected http: HttpClient) {}

  create(mUserRank: IMUserRank): Observable<EntityResponseType> {
    return this.http.post<IMUserRank>(this.resourceUrl, mUserRank, { observe: 'response' });
  }

  update(mUserRank: IMUserRank): Observable<EntityResponseType> {
    return this.http.put<IMUserRank>(this.resourceUrl, mUserRank, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMUserRank>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMUserRank[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
