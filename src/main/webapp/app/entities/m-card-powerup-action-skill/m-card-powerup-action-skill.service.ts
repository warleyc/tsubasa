import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

type EntityResponseType = HttpResponse<IMCardPowerupActionSkill>;
type EntityArrayResponseType = HttpResponse<IMCardPowerupActionSkill[]>;

@Injectable({ providedIn: 'root' })
export class MCardPowerupActionSkillService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-powerup-action-skills';

  constructor(protected http: HttpClient) {}

  create(mCardPowerupActionSkill: IMCardPowerupActionSkill): Observable<EntityResponseType> {
    return this.http.post<IMCardPowerupActionSkill>(this.resourceUrl, mCardPowerupActionSkill, { observe: 'response' });
  }

  update(mCardPowerupActionSkill: IMCardPowerupActionSkill): Observable<EntityResponseType> {
    return this.http.put<IMCardPowerupActionSkill>(this.resourceUrl, mCardPowerupActionSkill, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardPowerupActionSkill>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardPowerupActionSkill[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
