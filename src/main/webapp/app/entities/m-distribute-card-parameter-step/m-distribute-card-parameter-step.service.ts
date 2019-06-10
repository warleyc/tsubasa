import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';

type EntityResponseType = HttpResponse<IMDistributeCardParameterStep>;
type EntityArrayResponseType = HttpResponse<IMDistributeCardParameterStep[]>;

@Injectable({ providedIn: 'root' })
export class MDistributeCardParameterStepService {
  public resourceUrl = SERVER_API_URL + 'api/m-distribute-card-parameter-steps';

  constructor(protected http: HttpClient) {}

  create(mDistributeCardParameterStep: IMDistributeCardParameterStep): Observable<EntityResponseType> {
    return this.http.post<IMDistributeCardParameterStep>(this.resourceUrl, mDistributeCardParameterStep, { observe: 'response' });
  }

  update(mDistributeCardParameterStep: IMDistributeCardParameterStep): Observable<EntityResponseType> {
    return this.http.put<IMDistributeCardParameterStep>(this.resourceUrl, mDistributeCardParameterStep, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDistributeCardParameterStep>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDistributeCardParameterStep[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
