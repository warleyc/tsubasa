import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

type EntityResponseType = HttpResponse<IMModelUniformUp>;
type EntityArrayResponseType = HttpResponse<IMModelUniformUp[]>;

@Injectable({ providedIn: 'root' })
export class MModelUniformUpService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-uniform-ups';

  constructor(protected http: HttpClient) {}

  create(mModelUniformUp: IMModelUniformUp): Observable<EntityResponseType> {
    return this.http.post<IMModelUniformUp>(this.resourceUrl, mModelUniformUp, { observe: 'response' });
  }

  update(mModelUniformUp: IMModelUniformUp): Observable<EntityResponseType> {
    return this.http.put<IMModelUniformUp>(this.resourceUrl, mModelUniformUp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelUniformUp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelUniformUp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
