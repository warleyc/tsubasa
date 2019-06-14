import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';

type EntityResponseType = HttpResponse<IMMarathonBoostSchedule>;
type EntityArrayResponseType = HttpResponse<IMMarathonBoostSchedule[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonBoostScheduleService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-boost-schedules';

  constructor(protected http: HttpClient) {}

  create(mMarathonBoostSchedule: IMMarathonBoostSchedule): Observable<EntityResponseType> {
    return this.http.post<IMMarathonBoostSchedule>(this.resourceUrl, mMarathonBoostSchedule, { observe: 'response' });
  }

  update(mMarathonBoostSchedule: IMMarathonBoostSchedule): Observable<EntityResponseType> {
    return this.http.put<IMMarathonBoostSchedule>(this.resourceUrl, mMarathonBoostSchedule, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonBoostSchedule>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonBoostSchedule[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
