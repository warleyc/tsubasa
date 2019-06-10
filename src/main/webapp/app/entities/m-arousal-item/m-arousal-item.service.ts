import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMArousalItem } from 'app/shared/model/m-arousal-item.model';

type EntityResponseType = HttpResponse<IMArousalItem>;
type EntityArrayResponseType = HttpResponse<IMArousalItem[]>;

@Injectable({ providedIn: 'root' })
export class MArousalItemService {
  public resourceUrl = SERVER_API_URL + 'api/m-arousal-items';

  constructor(protected http: HttpClient) {}

  create(mArousalItem: IMArousalItem): Observable<EntityResponseType> {
    return this.http.post<IMArousalItem>(this.resourceUrl, mArousalItem, { observe: 'response' });
  }

  update(mArousalItem: IMArousalItem): Observable<EntityResponseType> {
    return this.http.put<IMArousalItem>(this.resourceUrl, mArousalItem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMArousalItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMArousalItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
