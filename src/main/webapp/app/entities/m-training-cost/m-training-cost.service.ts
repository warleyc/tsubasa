import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTrainingCost } from 'app/shared/model/m-training-cost.model';

type EntityResponseType = HttpResponse<IMTrainingCost>;
type EntityArrayResponseType = HttpResponse<IMTrainingCost[]>;

@Injectable({ providedIn: 'root' })
export class MTrainingCostService {
  public resourceUrl = SERVER_API_URL + 'api/m-training-costs';

  constructor(protected http: HttpClient) {}

  create(mTrainingCost: IMTrainingCost): Observable<EntityResponseType> {
    return this.http.post<IMTrainingCost>(this.resourceUrl, mTrainingCost, { observe: 'response' });
  }

  update(mTrainingCost: IMTrainingCost): Observable<EntityResponseType> {
    return this.http.put<IMTrainingCost>(this.resourceUrl, mTrainingCost, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTrainingCost>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTrainingCost[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
