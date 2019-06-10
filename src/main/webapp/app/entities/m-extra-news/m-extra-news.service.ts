import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMExtraNews } from 'app/shared/model/m-extra-news.model';

type EntityResponseType = HttpResponse<IMExtraNews>;
type EntityArrayResponseType = HttpResponse<IMExtraNews[]>;

@Injectable({ providedIn: 'root' })
export class MExtraNewsService {
  public resourceUrl = SERVER_API_URL + 'api/m-extra-news';

  constructor(protected http: HttpClient) {}

  create(mExtraNews: IMExtraNews): Observable<EntityResponseType> {
    return this.http.post<IMExtraNews>(this.resourceUrl, mExtraNews, { observe: 'response' });
  }

  update(mExtraNews: IMExtraNews): Observable<EntityResponseType> {
    return this.http.put<IMExtraNews>(this.resourceUrl, mExtraNews, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMExtraNews>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMExtraNews[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
