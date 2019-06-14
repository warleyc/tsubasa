import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';

type EntityResponseType = HttpResponse<IMModelUniformBottomResource>;
type EntityArrayResponseType = HttpResponse<IMModelUniformBottomResource[]>;

@Injectable({ providedIn: 'root' })
export class MModelUniformBottomResourceService {
  public resourceUrl = SERVER_API_URL + 'api/m-model-uniform-bottom-resources';

  constructor(protected http: HttpClient) {}

  create(mModelUniformBottomResource: IMModelUniformBottomResource): Observable<EntityResponseType> {
    return this.http.post<IMModelUniformBottomResource>(this.resourceUrl, mModelUniformBottomResource, { observe: 'response' });
  }

  update(mModelUniformBottomResource: IMModelUniformBottomResource): Observable<EntityResponseType> {
    return this.http.put<IMModelUniformBottomResource>(this.resourceUrl, mModelUniformBottomResource, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMModelUniformBottomResource>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMModelUniformBottomResource[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
