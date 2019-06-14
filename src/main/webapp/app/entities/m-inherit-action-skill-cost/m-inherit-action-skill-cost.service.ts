import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';

type EntityResponseType = HttpResponse<IMInheritActionSkillCost>;
type EntityArrayResponseType = HttpResponse<IMInheritActionSkillCost[]>;

@Injectable({ providedIn: 'root' })
export class MInheritActionSkillCostService {
  public resourceUrl = SERVER_API_URL + 'api/m-inherit-action-skill-costs';

  constructor(protected http: HttpClient) {}

  create(mInheritActionSkillCost: IMInheritActionSkillCost): Observable<EntityResponseType> {
    return this.http.post<IMInheritActionSkillCost>(this.resourceUrl, mInheritActionSkillCost, { observe: 'response' });
  }

  update(mInheritActionSkillCost: IMInheritActionSkillCost): Observable<EntityResponseType> {
    return this.http.put<IMInheritActionSkillCost>(this.resourceUrl, mInheritActionSkillCost, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMInheritActionSkillCost>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMInheritActionSkillCost[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
