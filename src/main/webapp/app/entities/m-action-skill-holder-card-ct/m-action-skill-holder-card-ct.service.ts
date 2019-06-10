import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';

type EntityResponseType = HttpResponse<IMActionSkillHolderCardCt>;
type EntityArrayResponseType = HttpResponse<IMActionSkillHolderCardCt[]>;

@Injectable({ providedIn: 'root' })
export class MActionSkillHolderCardCtService {
  public resourceUrl = SERVER_API_URL + 'api/m-action-skill-holder-card-cts';

  constructor(protected http: HttpClient) {}

  create(mActionSkillHolderCardCt: IMActionSkillHolderCardCt): Observable<EntityResponseType> {
    return this.http.post<IMActionSkillHolderCardCt>(this.resourceUrl, mActionSkillHolderCardCt, { observe: 'response' });
  }

  update(mActionSkillHolderCardCt: IMActionSkillHolderCardCt): Observable<EntityResponseType> {
    return this.http.put<IMActionSkillHolderCardCt>(this.resourceUrl, mActionSkillHolderCardCt, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMActionSkillHolderCardCt>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMActionSkillHolderCardCt[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
