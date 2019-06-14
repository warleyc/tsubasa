import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

type EntityResponseType = HttpResponse<IMModelUniformBottom>;
type EntityArrayResponseType = HttpResponse<IMModelUniformBottom[]>;

@Injectable({ providedIn: 'root' })
export class MModelUniformBottomService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-uniform-bottoms';

  constructor(protected http: HttpClient) {}

  create(mModelUniformBottom: IMModelUniformBottom): Observable<EntityResponseType> {
    return this.http.post<IMModelUniformBottom>(this.resourceUrl, mModelUniformBottom, { observe: 'response' });
  }

  update(mModelUniformBottom: IMModelUniformBottom): Observable<EntityResponseType> {
    return this.http.put<IMModelUniformBottom>(this.resourceUrl, mModelUniformBottom, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelUniformBottom>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelUniformBottom[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
