import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

type EntityResponseType = HttpResponse<IMRivalEncountCutin>;
type EntityArrayResponseType = HttpResponse<IMRivalEncountCutin[]>;

@Injectable({ providedIn: 'root' })
export class MRivalEncountCutinService {
  public resourceUrl = SERVER_API_URL + 'api/m-rival-encount-cutins';

  constructor(protected http: HttpClient) {}

  create(mRivalEncountCutin: IMRivalEncountCutin): Observable<EntityResponseType> {
    return this.http.post<IMRivalEncountCutin>(this.resourceUrl, mRivalEncountCutin, { observe: 'response' });
  }

  update(mRivalEncountCutin: IMRivalEncountCutin): Observable<EntityResponseType> {
    return this.http.put<IMRivalEncountCutin>(this.resourceUrl, mRivalEncountCutin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRivalEncountCutin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRivalEncountCutin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
