import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

type EntityResponseType = HttpResponse<IMAreaActionWeight>;
type EntityArrayResponseType = HttpResponse<IMAreaActionWeight[]>;

@Injectable({ providedIn: 'root' })
export class MAreaActionWeightService {
  public resourceUrl = SERVER_API_URL + 'api/m-area-action-weights';

  constructor(protected http: HttpClient) {}

  create(mAreaActionWeight: IMAreaActionWeight): Observable<EntityResponseType> {
    return this.http.post<IMAreaActionWeight>(this.resourceUrl, mAreaActionWeight, { observe: 'response' });
  }

  update(mAreaActionWeight: IMAreaActionWeight): Observable<EntityResponseType> {
    return this.http.put<IMAreaActionWeight>(this.resourceUrl, mAreaActionWeight, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAreaActionWeight>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAreaActionWeight[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
