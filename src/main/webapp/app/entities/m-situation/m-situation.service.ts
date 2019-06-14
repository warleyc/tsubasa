import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSituation } from 'app/shared/model/m-situation.model';

type EntityResponseType = HttpResponse<IMSituation>;
type EntityArrayResponseType = HttpResponse<IMSituation[]>;

@Injectable({ providedIn: 'root' })
export class MSituationService {
  public resourceUrl = SERVER_API_URL + 'api/m-situations';

  constructor(protected http: HttpClient) {}

  create(mSituation: IMSituation): Observable<EntityResponseType> {
    return this.http.post<IMSituation>(this.resourceUrl, mSituation, { observe: 'response' });
  }

  update(mSituation: IMSituation): Observable<EntityResponseType> {
    return this.http.put<IMSituation>(this.resourceUrl, mSituation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSituation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSituation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
