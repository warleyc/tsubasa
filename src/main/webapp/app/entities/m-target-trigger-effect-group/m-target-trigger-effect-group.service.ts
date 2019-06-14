import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

type EntityResponseType = HttpResponse<IMTargetTriggerEffectGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetTriggerEffectGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetTriggerEffectGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-trigger-effect-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetTriggerEffectGroup>(this.resourceUrl, mTargetTriggerEffectGroup, { observe: 'response' });
  }

  update(mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetTriggerEffectGroup>(this.resourceUrl, mTargetTriggerEffectGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetTriggerEffectGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetTriggerEffectGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
