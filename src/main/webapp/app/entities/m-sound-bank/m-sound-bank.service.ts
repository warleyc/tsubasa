import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSoundBank } from 'app/shared/model/m-sound-bank.model';

type EntityResponseType = HttpResponse<IMSoundBank>;
type EntityArrayResponseType = HttpResponse<IMSoundBank[]>;

@Injectable({ providedIn: 'root' })
export class MSoundBankService {
  public resourceUrl = SERVER_API_URL + 'api/m-sound-banks';

  constructor(protected http: HttpClient) {}

  create(mSoundBank: IMSoundBank): Observable<EntityResponseType> {
    return this.http.post<IMSoundBank>(this.resourceUrl, mSoundBank, { observe: 'response' });
  }

  update(mSoundBank: IMSoundBank): Observable<EntityResponseType> {
    return this.http.put<IMSoundBank>(this.resourceUrl, mSoundBank, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSoundBank>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSoundBank[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
