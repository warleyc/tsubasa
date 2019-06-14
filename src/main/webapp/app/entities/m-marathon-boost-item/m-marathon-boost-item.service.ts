import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';

type EntityResponseType = HttpResponse<IMMarathonBoostItem>;
type EntityArrayResponseType = HttpResponse<IMMarathonBoostItem[]>;

@Injectable({ providedIn: 'root' })
export class MMarathonBoostItemService {
  public resourceUrl = SERVER_API_URL + 'api/m-marathon-boost-items';

  constructor(protected http: HttpClient) {}

  create(mMarathonBoostItem: IMMarathonBoostItem): Observable<EntityResponseType> {
    return this.http.post<IMMarathonBoostItem>(this.resourceUrl, mMarathonBoostItem, { observe: 'response' });
  }

  update(mMarathonBoostItem: IMMarathonBoostItem): Observable<EntityResponseType> {
    return this.http.put<IMMarathonBoostItem>(this.resourceUrl, mMarathonBoostItem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMMarathonBoostItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMMarathonBoostItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
