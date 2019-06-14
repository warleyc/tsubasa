import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';

type EntityResponseType = HttpResponse<IMActionSkillHolderCardContent>;
type EntityArrayResponseType = HttpResponse<IMActionSkillHolderCardContent[]>;

@Injectable({ providedIn: 'root' })
export class MActionSkillHolderCardContentService {
  public resourceUrl = SERVER_API_URL + 'api/m-action-skill-holder-card-contents';

  constructor(protected http: HttpClient) {}

  create(mActionSkillHolderCardContent: IMActionSkillHolderCardContent): Observable<EntityResponseType> {
    return this.http.post<IMActionSkillHolderCardContent>(this.resourceUrl, mActionSkillHolderCardContent, { observe: 'response' });
  }

  update(mActionSkillHolderCardContent: IMActionSkillHolderCardContent): Observable<EntityResponseType> {
    return this.http.put<IMActionSkillHolderCardContent>(this.resourceUrl, mActionSkillHolderCardContent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMActionSkillHolderCardContent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMActionSkillHolderCardContent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
