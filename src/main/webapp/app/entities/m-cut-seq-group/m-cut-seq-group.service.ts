import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';

type EntityResponseType = HttpResponse<IMCutSeqGroup>;
type EntityArrayResponseType = HttpResponse<IMCutSeqGroup[]>;

@Injectable({ providedIn: 'root' })
export class MCutSeqGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-cut-seq-groups';

  constructor(protected http: HttpClient) {}

  create(mCutSeqGroup: IMCutSeqGroup): Observable<EntityResponseType> {
    return this.http.post<IMCutSeqGroup>(this.resourceUrl, mCutSeqGroup, { observe: 'response' });
  }

  update(mCutSeqGroup: IMCutSeqGroup): Observable<EntityResponseType> {
    return this.http.put<IMCutSeqGroup>(this.resourceUrl, mCutSeqGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCutSeqGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCutSeqGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
