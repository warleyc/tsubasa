import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

type EntityResponseType = HttpResponse<IMCombinationCutPosition>;
type EntityArrayResponseType = HttpResponse<IMCombinationCutPosition[]>;

@Injectable({ providedIn: 'root' })
export class MCombinationCutPositionService {
  public resourceUrl = SERVER_API_URL + 'api/m-combination-cut-positions';

  constructor(protected http: HttpClient) {}

  create(mCombinationCutPosition: IMCombinationCutPosition): Observable<EntityResponseType> {
    return this.http.post<IMCombinationCutPosition>(this.resourceUrl, mCombinationCutPosition, { observe: 'response' });
  }

  update(mCombinationCutPosition: IMCombinationCutPosition): Observable<EntityResponseType> {
    return this.http.put<IMCombinationCutPosition>(this.resourceUrl, mCombinationCutPosition, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCombinationCutPosition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCombinationCutPosition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
