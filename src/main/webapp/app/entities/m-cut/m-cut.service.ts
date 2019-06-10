import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCut } from 'app/shared/model/m-cut.model';

type EntityResponseType = HttpResponse<IMCut>;
type EntityArrayResponseType = HttpResponse<IMCut[]>;

@Injectable({ providedIn: 'root' })
export class MCutService {
  public resourceUrl = SERVER_API_URL + 'api/m-cuts';

  constructor(protected http: HttpClient) {}

  create(mCut: IMCut): Observable<EntityResponseType> {
    return this.http.post<IMCut>(this.resourceUrl, mCut, { observe: 'response' });
  }

  update(mCut: IMCut): Observable<EntityResponseType> {
    return this.http.put<IMCut>(this.resourceUrl, mCut, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCut>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCut[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
