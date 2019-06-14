import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';

type EntityResponseType = HttpResponse<IMGachaRenditionTrajectoryPhoenix>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionTrajectoryPhoenix[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryPhoenixService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-trajectory-phoenixes';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionTrajectoryPhoenix>(this.resourceUrl, mGachaRenditionTrajectoryPhoenix, { observe: 'response' });
  }

  update(mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionTrajectoryPhoenix>(this.resourceUrl, mGachaRenditionTrajectoryPhoenix, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionTrajectoryPhoenix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionTrajectoryPhoenix[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
