import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

type EntityResponseType = HttpResponse<IMActionSkillCutin>;
type EntityArrayResponseType = HttpResponse<IMActionSkillCutin[]>;

@Injectable({ providedIn: 'root' })
export class MActionSkillCutinService {
  public resourceUrl = SERVER_API_URL + 'api/m-action-skill-cutins';

  constructor(protected http: HttpClient) {}

  create(mActionSkillCutin: IMActionSkillCutin): Observable<EntityResponseType> {
    return this.http.post<IMActionSkillCutin>(this.resourceUrl, mActionSkillCutin, { observe: 'response' });
  }

  update(mActionSkillCutin: IMActionSkillCutin): Observable<EntityResponseType> {
    return this.http.put<IMActionSkillCutin>(this.resourceUrl, mActionSkillCutin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMActionSkillCutin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMActionSkillCutin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
