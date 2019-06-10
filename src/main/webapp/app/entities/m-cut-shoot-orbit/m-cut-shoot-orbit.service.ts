import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';

type EntityResponseType = HttpResponse<IMCutShootOrbit>;
type EntityArrayResponseType = HttpResponse<IMCutShootOrbit[]>;

@Injectable({ providedIn: 'root' })
export class MCutShootOrbitService {
  public resourceUrl = SERVER_API_URL + 'api/m-cut-shoot-orbits';

  constructor(protected http: HttpClient) {}

  create(mCutShootOrbit: IMCutShootOrbit): Observable<EntityResponseType> {
    return this.http.post<IMCutShootOrbit>(this.resourceUrl, mCutShootOrbit, { observe: 'response' });
  }

  update(mCutShootOrbit: IMCutShootOrbit): Observable<EntityResponseType> {
    return this.http.put<IMCutShootOrbit>(this.resourceUrl, mCutShootOrbit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCutShootOrbit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCutShootOrbit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
