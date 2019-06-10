import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';

type EntityResponseType = HttpResponse<IMEncountersBonus>;
type EntityArrayResponseType = HttpResponse<IMEncountersBonus[]>;

@Injectable({ providedIn: 'root' })
export class MEncountersBonusService {
  public resourceUrl = SERVER_API_URL + 'api/m-encounters-bonuses';

  constructor(protected http: HttpClient) {}

  create(mEncountersBonus: IMEncountersBonus): Observable<EntityResponseType> {
    return this.http.post<IMEncountersBonus>(this.resourceUrl, mEncountersBonus, { observe: 'response' });
  }

  update(mEncountersBonus: IMEncountersBonus): Observable<EntityResponseType> {
    return this.http.put<IMEncountersBonus>(this.resourceUrl, mEncountersBonus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEncountersBonus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEncountersBonus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
