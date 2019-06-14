import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';

type EntityResponseType = HttpResponse<IMSceneTutorialMessage>;
type EntityArrayResponseType = HttpResponse<IMSceneTutorialMessage[]>;

@Injectable({ providedIn: 'root' })
export class MSceneTutorialMessageService {
  public resourceUrl = SERVER_API_URL + 'api/m-scene-tutorial-messages';

  constructor(protected http: HttpClient) {}

  create(mSceneTutorialMessage: IMSceneTutorialMessage): Observable<EntityResponseType> {
    return this.http.post<IMSceneTutorialMessage>(this.resourceUrl, mSceneTutorialMessage, { observe: 'response' });
  }

  update(mSceneTutorialMessage: IMSceneTutorialMessage): Observable<EntityResponseType> {
    return this.http.put<IMSceneTutorialMessage>(this.resourceUrl, mSceneTutorialMessage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMSceneTutorialMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMSceneTutorialMessage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
