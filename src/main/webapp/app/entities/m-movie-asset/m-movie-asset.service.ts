import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMovieAsset } from 'app/shared/model/m-movie-asset.model';

type EntityResponseType = HttpResponse<IMMovieAsset>;
type EntityArrayResponseType = HttpResponse<IMMovieAsset[]>;

@Injectable({ providedIn: 'root' })
export class MMovieAssetService {
  public resourceUrl = SERVER_API_URL + 'api/m-movie-assets';

  constructor(protected http: HttpClient) {}

  create(mMovieAsset: IMMovieAsset): Observable<EntityResponseType> {
    return this.http.post<IMMovieAsset>(this.resourceUrl, mMovieAsset, { observe: 'response' });
  }

  update(mMovieAsset: IMMovieAsset): Observable<EntityResponseType> {
    return this.http.put<IMMovieAsset>(this.resourceUrl, mMovieAsset, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMovieAsset>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMovieAsset[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
