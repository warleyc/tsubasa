import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMedal } from 'app/shared/model/m-medal.model';

type EntityResponseType = HttpResponse<IMMedal>;
type EntityArrayResponseType = HttpResponse<IMMedal[]>;

@Injectable({ providedIn: 'root' })
export class MMedalService {
  public resourceUrl = SERVER_API_URL + 'api/m-medals';

  constructor(protected http: HttpClient) {}

  create(mMedal: IMMedal): Observable<EntityResponseType> {
    return this.http.post<IMMedal>(this.resourceUrl, mMedal, { observe: 'response' });
  }

  update(mMedal: IMMedal): Observable<EntityResponseType> {
    return this.http.put<IMMedal>(this.resourceUrl, mMedal, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMedal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMedal[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
