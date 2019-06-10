import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

type EntityResponseType = HttpResponse<IMEncountersCommandBranch>;
type EntityArrayResponseType = HttpResponse<IMEncountersCommandBranch[]>;

@Injectable({ providedIn: 'root' })
export class MEncountersCommandBranchService {
  public resourceUrl = SERVER_API_URL + 'api/m-encounters-command-branches';

  constructor(protected http: HttpClient) {}

  create(mEncountersCommandBranch: IMEncountersCommandBranch): Observable<EntityResponseType> {
    return this.http.post<IMEncountersCommandBranch>(this.resourceUrl, mEncountersCommandBranch, { observe: 'response' });
  }

  update(mEncountersCommandBranch: IMEncountersCommandBranch): Observable<EntityResponseType> {
    return this.http.put<IMEncountersCommandBranch>(this.resourceUrl, mEncountersCommandBranch, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMEncountersCommandBranch>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMEncountersCommandBranch[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
