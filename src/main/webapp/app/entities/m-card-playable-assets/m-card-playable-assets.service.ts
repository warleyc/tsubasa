import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';

type EntityResponseType = HttpResponse<IMCardPlayableAssets>;
type EntityArrayResponseType = HttpResponse<IMCardPlayableAssets[]>;

@Injectable({ providedIn: 'root' })
export class MCardPlayableAssetsService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-playable-assets';

  constructor(protected http: HttpClient) {}

  create(mCardPlayableAssets: IMCardPlayableAssets): Observable<EntityResponseType> {
    return this.http.post<IMCardPlayableAssets>(this.resourceUrl, mCardPlayableAssets, { observe: 'response' });
  }

  update(mCardPlayableAssets: IMCardPlayableAssets): Observable<EntityResponseType> {
    return this.http.put<IMCardPlayableAssets>(this.resourceUrl, mCardPlayableAssets, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardPlayableAssets>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardPlayableAssets[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
