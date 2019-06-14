import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

type EntityResponseType = HttpResponse<IMTargetPlayableCardGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetPlayableCardGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetPlayableCardGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-playable-card-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetPlayableCardGroup: IMTargetPlayableCardGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetPlayableCardGroup>(this.resourceUrl, mTargetPlayableCardGroup, { observe: 'response' });
  }

  update(mTargetPlayableCardGroup: IMTargetPlayableCardGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetPlayableCardGroup>(this.resourceUrl, mTargetPlayableCardGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetPlayableCardGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetPlayableCardGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
