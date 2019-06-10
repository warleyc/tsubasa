import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';

type EntityResponseType = HttpResponse<IMCardIllustAssets>;
type EntityArrayResponseType = HttpResponse<IMCardIllustAssets[]>;

@Injectable({ providedIn: 'root' })
export class MCardIllustAssetsService {
  public resourceUrl = SERVER_API_URL + 'api/m-card-illust-assets';

  constructor(protected http: HttpClient) {}

  create(mCardIllustAssets: IMCardIllustAssets): Observable<EntityResponseType> {
    return this.http.post<IMCardIllustAssets>(this.resourceUrl, mCardIllustAssets, { observe: 'response' });
  }

  update(mCardIllustAssets: IMCardIllustAssets): Observable<EntityResponseType> {
    return this.http.put<IMCardIllustAssets>(this.resourceUrl, mCardIllustAssets, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCardIllustAssets>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCardIllustAssets[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
