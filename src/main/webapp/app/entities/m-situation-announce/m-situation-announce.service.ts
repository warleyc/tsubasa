import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSituationAnnounce } from 'app/shared/model/m-situation-announce.model';

type EntityResponseType = HttpResponse<IMSituationAnnounce>;
type EntityArrayResponseType = HttpResponse<IMSituationAnnounce[]>;

@Injectable({ providedIn: 'root' })
export class MSituationAnnounceService {
  public resourceUrl = SERVER_API_URL + 'api/m-situation-announces';

  constructor(protected http: HttpClient) {}

  create(mSituationAnnounce: IMSituationAnnounce): Observable<EntityResponseType> {
    return this.http.post<IMSituationAnnounce>(this.resourceUrl, mSituationAnnounce, { observe: 'response' });
  }

  update(mSituationAnnounce: IMSituationAnnounce): Observable<EntityResponseType> {
    return this.http.put<IMSituationAnnounce>(this.resourceUrl, mSituationAnnounce, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSituationAnnounce>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSituationAnnounce[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
