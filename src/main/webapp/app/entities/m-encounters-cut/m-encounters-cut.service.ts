import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEncountersCut } from 'app/shared/model/m-encounters-cut.model';

type EntityResponseType = HttpResponse<IMEncountersCut>;
type EntityArrayResponseType = HttpResponse<IMEncountersCut[]>;

@Injectable({ providedIn: 'root' })
export class MEncountersCutService {
  public resourceUrl = SERVER_API_URL + 'api/m-encounters-cuts';

  constructor(protected http: HttpClient) {}

  create(mEncountersCut: IMEncountersCut): Observable<EntityResponseType> {
    return this.http.post<IMEncountersCut>(this.resourceUrl, mEncountersCut, { observe: 'response' });
  }

  update(mEncountersCut: IMEncountersCut): Observable<EntityResponseType> {
    return this.http.put<IMEncountersCut>(this.resourceUrl, mEncountersCut, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEncountersCut>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEncountersCut[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
