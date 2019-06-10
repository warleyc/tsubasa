import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';

type EntityResponseType = HttpResponse<IMDetachActionSkillCost>;
type EntityArrayResponseType = HttpResponse<IMDetachActionSkillCost[]>;

@Injectable({ providedIn: 'root' })
export class MDetachActionSkillCostService {
  public resourceUrl = SERVER_API_URL + 'api/m-detach-action-skill-costs';

  constructor(protected http: HttpClient) {}

  create(mDetachActionSkillCost: IMDetachActionSkillCost): Observable<EntityResponseType> {
    return this.http.post<IMDetachActionSkillCost>(this.resourceUrl, mDetachActionSkillCost, { observe: 'response' });
  }

  update(mDetachActionSkillCost: IMDetachActionSkillCost): Observable<EntityResponseType> {
    return this.http.put<IMDetachActionSkillCost>(this.resourceUrl, mDetachActionSkillCost, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDetachActionSkillCost>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDetachActionSkillCost[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
