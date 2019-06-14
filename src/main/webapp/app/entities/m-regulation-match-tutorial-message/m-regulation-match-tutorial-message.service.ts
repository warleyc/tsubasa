import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';

type EntityResponseType = HttpResponse<IMRegulationMatchTutorialMessage>;
type EntityArrayResponseType = HttpResponse<IMRegulationMatchTutorialMessage[]>;

@Injectable({ providedIn: 'root' })
export class MRegulationMatchTutorialMessageService {
  public resourceUrl = SERVER_API_URL + 'api/m-regulation-match-tutorial-messages';

  constructor(protected http: HttpClient) {}

  create(mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage): Observable<EntityResponseType> {
    return this.http.post<IMRegulationMatchTutorialMessage>(this.resourceUrl, mRegulationMatchTutorialMessage, { observe: 'response' });
  }

  update(mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage): Observable<EntityResponseType> {
    return this.http.put<IMRegulationMatchTutorialMessage>(this.resourceUrl, mRegulationMatchTutorialMessage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMRegulationMatchTutorialMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMRegulationMatchTutorialMessage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
