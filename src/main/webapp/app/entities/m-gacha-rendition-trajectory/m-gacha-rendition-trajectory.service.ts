import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';

type EntityResponseType = HttpResponse<IMGachaRenditionTrajectory>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionTrajectory[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-trajectories';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionTrajectory: IMGachaRenditionTrajectory): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionTrajectory>(this.resourceUrl, mGachaRenditionTrajectory, { observe: 'response' });
  }

  update(mGachaRenditionTrajectory: IMGachaRenditionTrajectory): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionTrajectory>(this.resourceUrl, mGachaRenditionTrajectory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionTrajectory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionTrajectory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
