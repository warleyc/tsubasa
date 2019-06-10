import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';

type EntityResponseType = HttpResponse<IMApRecoveryItem>;
type EntityArrayResponseType = HttpResponse<IMApRecoveryItem[]>;

@Injectable({ providedIn: 'root' })
export class MApRecoveryItemService {
  public resourceUrl = SERVER_API_URL + 'api/m-ap-recovery-items';

  constructor(protected http: HttpClient) {}

  create(mApRecoveryItem: IMApRecoveryItem): Observable<EntityResponseType> {
    return this.http.post<IMApRecoveryItem>(this.resourceUrl, mApRecoveryItem, { observe: 'response' });
  }

  update(mApRecoveryItem: IMApRecoveryItem): Observable<EntityResponseType> {
    return this.http.put<IMApRecoveryItem>(this.resourceUrl, mApRecoveryItem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMApRecoveryItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMApRecoveryItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
