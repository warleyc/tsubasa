import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';

type EntityResponseType = HttpResponse<IMTimeattackQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMTimeattackQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-timeattack-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mTimeattackQuestWorld: IMTimeattackQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMTimeattackQuestWorld>(this.resourceUrl, mTimeattackQuestWorld, { observe: 'response' });
  }

  update(mTimeattackQuestWorld: IMTimeattackQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMTimeattackQuestWorld>(this.resourceUrl, mTimeattackQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTimeattackQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTimeattackQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
