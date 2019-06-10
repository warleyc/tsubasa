import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAchievement } from 'app/shared/model/m-achievement.model';

type EntityResponseType = HttpResponse<IMAchievement>;
type EntityArrayResponseType = HttpResponse<IMAchievement[]>;

@Injectable({ providedIn: 'root' })
export class MAchievementService {
  public resourceUrl = SERVER_API_URL + 'api/m-achievements';

  constructor(protected http: HttpClient) {}

  create(mAchievement: IMAchievement): Observable<EntityResponseType> {
    return this.http.post<IMAchievement>(this.resourceUrl, mAchievement, { observe: 'response' });
  }

  update(mAchievement: IMAchievement): Observable<EntityResponseType> {
    return this.http.put<IMAchievement>(this.resourceUrl, mAchievement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAchievement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAchievement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
