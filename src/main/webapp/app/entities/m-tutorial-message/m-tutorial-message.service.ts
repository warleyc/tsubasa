import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTutorialMessage } from 'app/shared/model/m-tutorial-message.model';

type EntityResponseType = HttpResponse<IMTutorialMessage>;
type EntityArrayResponseType = HttpResponse<IMTutorialMessage[]>;

@Injectable({ providedIn: 'root' })
export class MTutorialMessageService {
  public resourceUrl = SERVER_API_URL + 'api/m-tutorial-messages';

  constructor(protected http: HttpClient) {}

  create(mTutorialMessage: IMTutorialMessage): Observable<EntityResponseType> {
    return this.http.post<IMTutorialMessage>(this.resourceUrl, mTutorialMessage, { observe: 'response' });
  }

  update(mTutorialMessage: IMTutorialMessage): Observable<EntityResponseType> {
    return this.http.put<IMTutorialMessage>(this.resourceUrl, mTutorialMessage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTutorialMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTutorialMessage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
