import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';

type EntityResponseType = HttpResponse<IMEncountersCommandCompatibility>;
type EntityArrayResponseType = HttpResponse<IMEncountersCommandCompatibility[]>;

@Injectable({ providedIn: 'root' })
export class MEncountersCommandCompatibilityService {
  public resourceUrl = SERVER_API_URL + 'api/m-encounters-command-compatibilities';

  constructor(protected http: HttpClient) {}

  create(mEncountersCommandCompatibility: IMEncountersCommandCompatibility): Observable<EntityResponseType> {
    return this.http.post<IMEncountersCommandCompatibility>(this.resourceUrl, mEncountersCommandCompatibility, { observe: 'response' });
  }

  update(mEncountersCommandCompatibility: IMEncountersCommandCompatibility): Observable<EntityResponseType> {
    return this.http.put<IMEncountersCommandCompatibility>(this.resourceUrl, mEncountersCommandCompatibility, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEncountersCommandCompatibility>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEncountersCommandCompatibility[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
