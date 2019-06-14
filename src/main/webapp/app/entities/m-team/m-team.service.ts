import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTeam } from 'app/shared/model/m-team.model';

type EntityResponseType = HttpResponse<IMTeam>;
type EntityArrayResponseType = HttpResponse<IMTeam[]>;

@Injectable({ providedIn: 'root' })
export class MTeamService {
  public resourceUrl = SERVER_API_URL + 'api/m-teams';

  constructor(protected http: HttpClient) {}

  create(mTeam: IMTeam): Observable<EntityResponseType> {
    return this.http.post<IMTeam>(this.resourceUrl, mTeam, { observe: 'response' });
  }

  update(mTeam: IMTeam): Observable<EntityResponseType> {
    return this.http.put<IMTeam>(this.resourceUrl, mTeam, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTeam>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTeam[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
