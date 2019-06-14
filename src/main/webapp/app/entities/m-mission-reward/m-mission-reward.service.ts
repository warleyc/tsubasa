import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';

type EntityResponseType = HttpResponse<IMMissionReward>;
type EntityArrayResponseType = HttpResponse<IMMissionReward[]>;

@Injectable({ providedIn: 'root' })
export class MMissionRewardService {
  public resourceUrl = SERVER_API_URL + 'api/m-mission-rewards';

  constructor(protected http: HttpClient) {}

  create(mMissionReward: IMMissionReward): Observable<EntityResponseType> {
    return this.http.post<IMMissionReward>(this.resourceUrl, mMissionReward, { observe: 'response' });
  }

  update(mMissionReward: IMMissionReward): Observable<EntityResponseType> {
    return this.http.put<IMMissionReward>(this.resourceUrl, mMissionReward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMissionReward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMissionReward[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
