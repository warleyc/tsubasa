import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';

type EntityResponseType = HttpResponse<IMChallengeQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMChallengeQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MChallengeQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-challenge-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mChallengeQuestWorld: IMChallengeQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMChallengeQuestWorld>(this.resourceUrl, mChallengeQuestWorld, { observe: 'response' });
  }

  update(mChallengeQuestWorld: IMChallengeQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMChallengeQuestWorld>(this.resourceUrl, mChallengeQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMChallengeQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMChallengeQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
