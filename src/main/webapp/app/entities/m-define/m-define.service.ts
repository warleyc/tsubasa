import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDefine } from 'app/shared/model/m-define.model';

type EntityResponseType = HttpResponse<IMDefine>;
type EntityArrayResponseType = HttpResponse<IMDefine[]>;

@Injectable({ providedIn: 'root' })
export class MDefineService {
  public resourceUrl = SERVER_API_URL + 'api/m-defines';

  constructor(protected http: HttpClient) {}

  create(mDefine: IMDefine): Observable<EntityResponseType> {
    return this.http.post<IMDefine>(this.resourceUrl, mDefine, { observe: 'response' });
  }

  update(mDefine: IMDefine): Observable<EntityResponseType> {
    return this.http.put<IMDefine>(this.resourceUrl, mDefine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDefine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDefine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
