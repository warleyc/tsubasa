import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

type EntityResponseType = HttpResponse<IMGuerillaQuestWorld>;
type EntityArrayResponseType = HttpResponse<IMGuerillaQuestWorld[]>;

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestWorldService {
  public resourceUrl = SERVER_API_URL + 'api/m-guerilla-quest-worlds';

  constructor(protected http: HttpClient) {}

  create(mGuerillaQuestWorld: IMGuerillaQuestWorld): Observable<EntityResponseType> {
    return this.http.post<IMGuerillaQuestWorld>(this.resourceUrl, mGuerillaQuestWorld, { observe: 'response' });
  }

  update(mGuerillaQuestWorld: IMGuerillaQuestWorld): Observable<EntityResponseType> {
    return this.http.put<IMGuerillaQuestWorld>(this.resourceUrl, mGuerillaQuestWorld, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGuerillaQuestWorld>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGuerillaQuestWorld[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
