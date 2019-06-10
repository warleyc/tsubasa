import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMArousalMaterial } from 'app/shared/model/m-arousal-material.model';

type EntityResponseType = HttpResponse<IMArousalMaterial>;
type EntityArrayResponseType = HttpResponse<IMArousalMaterial[]>;

@Injectable({ providedIn: 'root' })
export class MArousalMaterialService {
  public resourceUrl = SERVER_API_URL + 'api/m-arousal-materials';

  constructor(protected http: HttpClient) {}

  create(mArousalMaterial: IMArousalMaterial): Observable<EntityResponseType> {
    return this.http.post<IMArousalMaterial>(this.resourceUrl, mArousalMaterial, { observe: 'response' });
  }

  update(mArousalMaterial: IMArousalMaterial): Observable<EntityResponseType> {
    return this.http.put<IMArousalMaterial>(this.resourceUrl, mArousalMaterial, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMArousalMaterial>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMArousalMaterial[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
