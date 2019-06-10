import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';

type EntityResponseType = HttpResponse<IMDistributeCardParameter>;
type EntityArrayResponseType = HttpResponse<IMDistributeCardParameter[]>;

@Injectable({ providedIn: 'root' })
export class MDistributeCardParameterService {
  public resourceUrl = SERVER_API_URL + 'api/m-distribute-card-parameters';

  constructor(protected http: HttpClient) {}

  create(mDistributeCardParameter: IMDistributeCardParameter): Observable<EntityResponseType> {
    return this.http.post<IMDistributeCardParameter>(this.resourceUrl, mDistributeCardParameter, { observe: 'response' });
  }

  update(mDistributeCardParameter: IMDistributeCardParameter): Observable<EntityResponseType> {
    return this.http.put<IMDistributeCardParameter>(this.resourceUrl, mDistributeCardParameter, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDistributeCardParameter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDistributeCardParameter[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
