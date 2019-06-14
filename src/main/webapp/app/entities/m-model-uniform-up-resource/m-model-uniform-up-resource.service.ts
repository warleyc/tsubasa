import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';

type EntityResponseType = HttpResponse<IMModelUniformUpResource>;
type EntityArrayResponseType = HttpResponse<IMModelUniformUpResource[]>;

@Injectable({ providedIn: 'root' })
export class MModelUniformUpResourceService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-uniform-up-resources';

  constructor(protected http: HttpClient) {}

  create(mModelUniformUpResource: IMModelUniformUpResource): Observable<EntityResponseType> {
    return this.http.post<IMModelUniformUpResource>(this.resourceUrl, mModelUniformUpResource, { observe: 'response' });
  }

  update(mModelUniformUpResource: IMModelUniformUpResource): Observable<EntityResponseType> {
    return this.http.put<IMModelUniformUpResource>(this.resourceUrl, mModelUniformUpResource, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelUniformUpResource>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelUniformUpResource[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
