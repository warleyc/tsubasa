import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMatchFormation } from 'app/shared/model/m-match-formation.model';

type EntityResponseType = HttpResponse<IMMatchFormation>;
type EntityArrayResponseType = HttpResponse<IMMatchFormation[]>;

@Injectable({ providedIn: 'root' })
export class MMatchFormationService {
  public resourceUrl = SERVER_API_URL + 'api/m-match-formations';

  constructor(protected http: HttpClient) {}

  create(mMatchFormation: IMMatchFormation): Observable<EntityResponseType> {
    return this.http.post<IMMatchFormation>(this.resourceUrl, mMatchFormation, { observe: 'response' });
  }

  update(mMatchFormation: IMMatchFormation): Observable<EntityResponseType> {
    return this.http.put<IMMatchFormation>(this.resourceUrl, mMatchFormation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMatchFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMatchFormation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
