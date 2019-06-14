import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPvpWave } from 'app/shared/model/m-pvp-wave.model';

type EntityResponseType = HttpResponse<IMPvpWave>;
type EntityArrayResponseType = HttpResponse<IMPvpWave[]>;

@Injectable({ providedIn: 'root' })
export class MPvpWaveService {
  public resourceUrl = SERVER_API_URL + 'api/m-pvp-waves';

  constructor(protected http: HttpClient) {}

  create(mPvpWave: IMPvpWave): Observable<EntityResponseType> {
    return this.http.post<IMPvpWave>(this.resourceUrl, mPvpWave, { observe: 'response' });
  }

  update(mPvpWave: IMPvpWave): Observable<EntityResponseType> {
    return this.http.put<IMPvpWave>(this.resourceUrl, mPvpWave, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPvpWave>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPvpWave[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
