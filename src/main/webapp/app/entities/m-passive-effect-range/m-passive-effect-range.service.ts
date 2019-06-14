import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

type EntityResponseType = HttpResponse<IMPassiveEffectRange>;
type EntityArrayResponseType = HttpResponse<IMPassiveEffectRange[]>;

@Injectable({ providedIn: 'root' })
export class MPassiveEffectRangeService {
  public resourceUrl = SERVER_API_URL + 'api/m-passive-effect-ranges';

  constructor(protected http: HttpClient) {}

  create(mPassiveEffectRange: IMPassiveEffectRange): Observable<EntityResponseType> {
    return this.http.post<IMPassiveEffectRange>(this.resourceUrl, mPassiveEffectRange, { observe: 'response' });
  }

  update(mPassiveEffectRange: IMPassiveEffectRange): Observable<EntityResponseType> {
    return this.http.put<IMPassiveEffectRange>(this.resourceUrl, mPassiveEffectRange, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMPassiveEffectRange>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMPassiveEffectRange[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
