import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDbMapping } from 'app/shared/model/m-db-mapping.model';

type EntityResponseType = HttpResponse<IMDbMapping>;
type EntityArrayResponseType = HttpResponse<IMDbMapping[]>;

@Injectable({ providedIn: 'root' })
export class MDbMappingService {
  public resourceUrl = SERVER_API_URL + 'api/m-db-mappings';

  constructor(protected http: HttpClient) {}

  create(mDbMapping: IMDbMapping): Observable<EntityResponseType> {
    return this.http.post<IMDbMapping>(this.resourceUrl, mDbMapping, { observe: 'response' });
  }

  update(mDbMapping: IMDbMapping): Observable<EntityResponseType> {
    return this.http.put<IMDbMapping>(this.resourceUrl, mDbMapping, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDbMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDbMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
