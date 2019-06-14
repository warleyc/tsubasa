import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';

type EntityResponseType = HttpResponse<IMSoundBankEvent>;
type EntityArrayResponseType = HttpResponse<IMSoundBankEvent[]>;

@Injectable({ providedIn: 'root' })
export class MSoundBankEventService {
  public resourceUrl = SERVER_API_URL + 'api/m-sound-bank-events';

  constructor(protected http: HttpClient) {}

  create(mSoundBankEvent: IMSoundBankEvent): Observable<EntityResponseType> {
    return this.http.post<IMSoundBankEvent>(this.resourceUrl, mSoundBankEvent, { observe: 'response' });
  }

  update(mSoundBankEvent: IMSoundBankEvent): Observable<EntityResponseType> {
    return this.http.put<IMSoundBankEvent>(this.resourceUrl, mSoundBankEvent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSoundBankEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSoundBankEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
