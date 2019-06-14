import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';

type EntityResponseType = HttpResponse<IMTargetRarityGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetRarityGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetRarityGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-rarity-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetRarityGroup: IMTargetRarityGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetRarityGroup>(this.resourceUrl, mTargetRarityGroup, { observe: 'response' });
  }

  update(mTargetRarityGroup: IMTargetRarityGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetRarityGroup>(this.resourceUrl, mTargetRarityGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetRarityGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetRarityGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
