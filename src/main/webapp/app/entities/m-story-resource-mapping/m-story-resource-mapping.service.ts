import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';

type EntityResponseType = HttpResponse<IMStoryResourceMapping>;
type EntityArrayResponseType = HttpResponse<IMStoryResourceMapping[]>;

@Injectable({ providedIn: 'root' })
export class MStoryResourceMappingService {
  public resourceUrl = SERVER_API_URL + 'api/m-story-resource-mappings';

  constructor(protected http: HttpClient) {}

  create(mStoryResourceMapping: IMStoryResourceMapping): Observable<EntityResponseType> {
    return this.http.post<IMStoryResourceMapping>(this.resourceUrl, mStoryResourceMapping, { observe: 'response' });
  }

  update(mStoryResourceMapping: IMStoryResourceMapping): Observable<EntityResponseType> {
    return this.http.put<IMStoryResourceMapping>(this.resourceUrl, mStoryResourceMapping, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMStoryResourceMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMStoryResourceMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
