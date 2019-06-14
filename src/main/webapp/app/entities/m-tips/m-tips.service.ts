import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTips } from 'app/shared/model/m-tips.model';

type EntityResponseType = HttpResponse<IMTips>;
type EntityArrayResponseType = HttpResponse<IMTips[]>;

@Injectable({ providedIn: 'root' })
export class MTipsService {
  public resourceUrl = SERVER_API_URL + 'api/m-tips';

  constructor(protected http: HttpClient) {}

  create(mTips: IMTips): Observable<EntityResponseType> {
    return this.http.post<IMTips>(this.resourceUrl, mTips, { observe: 'response' });
  }

  update(mTips: IMTips): Observable<EntityResponseType> {
    return this.http.put<IMTips>(this.resourceUrl, mTips, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTips>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTips[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
