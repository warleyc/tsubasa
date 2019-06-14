import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuildMission } from 'app/shared/model/m-guild-mission.model';

type EntityResponseType = HttpResponse<IMGuildMission>;
type EntityArrayResponseType = HttpResponse<IMGuildMission[]>;

@Injectable({ providedIn: 'root' })
export class MGuildMissionService {
  public resourceUrl = SERVER_API_URL + 'api/m-guild-missions';

  constructor(protected http: HttpClient) {}

  create(mGuildMission: IMGuildMission): Observable<EntityResponseType> {
    return this.http.post<IMGuildMission>(this.resourceUrl, mGuildMission, { observe: 'response' });
  }

  update(mGuildMission: IMGuildMission): Observable<EntityResponseType> {
    return this.http.put<IMGuildMission>(this.resourceUrl, mGuildMission, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuildMission>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuildMission[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
