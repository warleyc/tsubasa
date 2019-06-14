import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGoalJudgement } from 'app/shared/model/m-goal-judgement.model';

type EntityResponseType = HttpResponse<IMGoalJudgement>;
type EntityArrayResponseType = HttpResponse<IMGoalJudgement[]>;

@Injectable({ providedIn: 'root' })
export class MGoalJudgementService {
  public resourceUrl = SERVER_API_URL + 'api/m-goal-judgements';

  constructor(protected http: HttpClient) {}

  create(mGoalJudgement: IMGoalJudgement): Observable<EntityResponseType> {
    return this.http.post<IMGoalJudgement>(this.resourceUrl, mGoalJudgement, { observe: 'response' });
  }

  update(mGoalJudgement: IMGoalJudgement): Observable<EntityResponseType> {
    return this.http.put<IMGoalJudgement>(this.resourceUrl, mGoalJudgement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGoalJudgement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGoalJudgement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
