import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMHomeMarquee } from 'app/shared/model/m-home-marquee.model';

type EntityResponseType = HttpResponse<IMHomeMarquee>;
type EntityArrayResponseType = HttpResponse<IMHomeMarquee[]>;

@Injectable({ providedIn: 'root' })
export class MHomeMarqueeService {
  public resourceUrl = SERVER_API_URL + 'api/m-home-marquees';

  constructor(protected http: HttpClient) {}

  create(mHomeMarquee: IMHomeMarquee): Observable<EntityResponseType> {
    return this.http.post<IMHomeMarquee>(this.resourceUrl, mHomeMarquee, { observe: 'response' });
  }

  update(mHomeMarquee: IMHomeMarquee): Observable<EntityResponseType> {
    return this.http.put<IMHomeMarquee>(this.resourceUrl, mHomeMarquee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMHomeMarquee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMHomeMarquee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
