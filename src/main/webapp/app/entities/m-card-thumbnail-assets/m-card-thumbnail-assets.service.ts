import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';

type EntityResponseType = HttpResponse<IMCardThumbnailAssets>;
type EntityArrayResponseType = HttpResponse<IMCardThumbnailAssets[]>;

@Injectable({ providedIn: 'root' })
export class MCardThumbnailAssetsService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-thumbnail-assets';

  constructor(protected http: HttpClient) {}

  create(mCardThumbnailAssets: IMCardThumbnailAssets): Observable<EntityResponseType> {
    return this.http.post<IMCardThumbnailAssets>(this.resourceUrl, mCardThumbnailAssets, { observe: 'response' });
  }

  update(mCardThumbnailAssets: IMCardThumbnailAssets): Observable<EntityResponseType> {
    return this.http.put<IMCardThumbnailAssets>(this.resourceUrl, mCardThumbnailAssets, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardThumbnailAssets>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardThumbnailAssets[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
