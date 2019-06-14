import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMNationality } from 'app/shared/model/m-nationality.model';

type EntityResponseType = HttpResponse<IMNationality>;
type EntityArrayResponseType = HttpResponse<IMNationality[]>;

@Injectable({ providedIn: 'root' })
export class MNationalityService {
  public resourceUrl = SERVER_API_URL + 'api/m-nationalities';

  constructor(protected http: HttpClient) {}

  create(mNationality: IMNationality): Observable<EntityResponseType> {
    return this.http.post<IMNationality>(this.resourceUrl, mNationality, { observe: 'response' });
  }

  update(mNationality: IMNationality): Observable<EntityResponseType> {
    return this.http.put<IMNationality>(this.resourceUrl, mNationality, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMNationality>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMNationality[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
