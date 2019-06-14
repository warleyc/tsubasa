import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMission } from 'app/shared/model/m-mission.model';

type EntityResponseType = HttpResponse<IMMission>;
type EntityArrayResponseType = HttpResponse<IMMission[]>;

@Injectable({ providedIn: 'root' })
export class MMissionService {
  public resourceUrl = SERVER_API_URL + 'api/m-missions';

  constructor(protected http: HttpClient) {}

  create(mMission: IMMission): Observable<EntityResponseType> {
    return this.http.post<IMMission>(this.resourceUrl, mMission, { observe: 'response' });
  }

  update(mMission: IMMission): Observable<EntityResponseType> {
    return this.http.put<IMMission>(this.resourceUrl, mMission, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMission>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMission[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
