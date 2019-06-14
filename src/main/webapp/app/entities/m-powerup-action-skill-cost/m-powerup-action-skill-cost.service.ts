import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';

type EntityResponseType = HttpResponse<IMPowerupActionSkillCost>;
type EntityArrayResponseType = HttpResponse<IMPowerupActionSkillCost[]>;

@Injectable({ providedIn: 'root' })
export class MPowerupActionSkillCostService {
  public resourceUrl = SERVER_API_URL + 'api/m-powerup-action-skill-costs';

  constructor(protected http: HttpClient) {}

  create(mPowerupActionSkillCost: IMPowerupActionSkillCost): Observable<EntityResponseType> {
    return this.http.post<IMPowerupActionSkillCost>(this.resourceUrl, mPowerupActionSkillCost, { observe: 'response' });
  }

  update(mPowerupActionSkillCost: IMPowerupActionSkillCost): Observable<EntityResponseType> {
    return this.http.put<IMPowerupActionSkillCost>(this.resourceUrl, mPowerupActionSkillCost, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPowerupActionSkillCost>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPowerupActionSkillCost[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
