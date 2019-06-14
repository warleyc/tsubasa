import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

type EntityResponseType = HttpResponse<IMGachaRenditionTrajectoryCutIn>;
type EntityArrayResponseType = HttpResponse<IMGachaRenditionTrajectoryCutIn[]>;

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryCutInService {
  public resourceUrl = SERVER_API_URL + 'api/m-gacha-rendition-trajectory-cut-ins';

  constructor(protected http: HttpClient) {}

  create(mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn): Observable<EntityResponseType> {
    return this.http.post<IMGachaRenditionTrajectoryCutIn>(this.resourceUrl, mGachaRenditionTrajectoryCutIn, { observe: 'response' });
  }

  update(mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn): Observable<EntityResponseType> {
    return this.http.put<IMGachaRenditionTrajectoryCutIn>(this.resourceUrl, mGachaRenditionTrajectoryCutIn, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMGachaRenditionTrajectoryCutIn>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMGachaRenditionTrajectoryCutIn[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
