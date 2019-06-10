import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';

type EntityResponseType = HttpResponse<IMAdventQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMAdventQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MAdventQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-advent-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mAdventQuestWorld: IMAdventQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMAdventQuestWorld>(this.resourceUrl, mAdventQuestWorld, { observe: 'response' });
  }

  update(mAdventQuestWorld: IMAdventQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMAdventQuestWorld>(this.resourceUrl, mAdventQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAdventQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAdventQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
