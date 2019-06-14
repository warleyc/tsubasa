import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';

type EntityResponseType = HttpResponse<IMTriggerEffectRange>;
type EntityArrayResponseType = HttpResponse<IMTriggerEffectRange[]>;

@Injectable({ providedIn: 'root' })
export class MTriggerEffectRangeService {
  public resourceUrl = SERVER_API_URL + 'api/m-trigger-effect-ranges';

  constructor(protected http: HttpClient) {}

  create(mTriggerEffectRange: IMTriggerEffectRange): Observable<EntityResponseType> {
    return this.http.post<IMTriggerEffectRange>(this.resourceUrl, mTriggerEffectRange, { observe: 'response' });
  }

  update(mTriggerEffectRange: IMTriggerEffectRange): Observable<EntityResponseType> {
    return this.http.put<IMTriggerEffectRange>(this.resourceUrl, mTriggerEffectRange, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTriggerEffectRange>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTriggerEffectRange[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
