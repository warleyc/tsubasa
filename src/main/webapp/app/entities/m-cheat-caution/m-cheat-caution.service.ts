import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMCheatCaution } from 'app/shared/model/m-cheat-caution.model';

type EntityResponseType = HttpResponse<IMCheatCaution>;
type EntityArrayResponseType = HttpResponse<IMCheatCaution[]>;

@Injectable({ providedIn: 'root' })
export class MCheatCautionService {
  public resourceUrl = SERVER_API_URL + 'api/m-cheat-cautions';

  constructor(protected http: HttpClient) {}

  create(mCheatCaution: IMCheatCaution): Observable<EntityResponseType> {
    return this.http.post<IMCheatCaution>(this.resourceUrl, mCheatCaution, { observe: 'response' });
  }

  update(mCheatCaution: IMCheatCaution): Observable<EntityResponseType> {
    return this.http.put<IMCheatCaution>(this.resourceUrl, mCheatCaution, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMCheatCaution>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMCheatCaution[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
