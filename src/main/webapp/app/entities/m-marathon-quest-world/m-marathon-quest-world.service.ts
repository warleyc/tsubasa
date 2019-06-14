import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';

type EntityResponseType = HttpResponse<IMMarathonQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMMarathonQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mMarathonQuestWorld: IMMarathonQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMMarathonQuestWorld>(this.resourceUrl, mMarathonQuestWorld, { observe: 'response' });
  }

  update(mMarathonQuestWorld: IMMarathonQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMMarathonQuestWorld>(this.resourceUrl, mMarathonQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
