import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMLeagueRegulation } from 'app/shared/model/m-league-regulation.model';

type EntityResponseType = HttpResponse<IMLeagueRegulation>;
type EntityArrayResponseType = HttpResponse<IMLeagueRegulation[]>;

@Injectable({ providedIn: 'root' })
export class MLeagueRegulationService {
  public resourceUrl = SERVER_API_URL + 'api/m-league-regulations';

  constructor(protected http: HttpClient) {}

  create(mLeagueRegulation: IMLeagueRegulation): Observable<EntityResponseType> {
    return this.http.post<IMLeagueRegulation>(this.resourceUrl, mLeagueRegulation, { observe: 'response' });
  }

  update(mLeagueRegulation: IMLeagueRegulation): Observable<EntityResponseType> {
    return this.http.put<IMLeagueRegulation>(this.resourceUrl, mLeagueRegulation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMLeagueRegulation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMLeagueRegulation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
