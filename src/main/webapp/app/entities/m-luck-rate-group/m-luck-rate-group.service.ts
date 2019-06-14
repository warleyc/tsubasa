import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';

type EntityResponseType = HttpResponse<IMLuckRateGroup>;
type EntityArrayResponseType = HttpResponse<IMLuckRateGroup[]>;

@Injectable({ providedIn: 'root' })
export class MLuckRateGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-luck-rate-groups';

  constructor(protected http: HttpClient) {}

  create(mLuckRateGroup: IMLuckRateGroup): Observable<EntityResponseType> {
    return this.http.post<IMLuckRateGroup>(this.resourceUrl, mLuckRateGroup, { observe: 'response' });
  }

  update(mLuckRateGroup: IMLuckRateGroup): Observable<EntityResponseType> {
    return this.http.put<IMLuckRateGroup>(this.resourceUrl, mLuckRateGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLuckRateGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLuckRateGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
