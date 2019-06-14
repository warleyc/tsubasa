import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMStageStory } from 'app/shared/model/m-stage-story.model';

type EntityResponseType = HttpResponse<IMStageStory>;
type EntityArrayResponseType = HttpResponse<IMStageStory[]>;

@Injectable({ providedIn: 'root' })
export class MStageStoryService {
  public resourceUrl = SERVER_API_URL + 'api/m-stage-stories';

  constructor(protected http: HttpClient) {}

  create(mStageStory: IMStageStory): Observable<EntityResponseType> {
    return this.http.post<IMStageStory>(this.resourceUrl, mStageStory, { observe: 'response' });
  }

  update(mStageStory: IMStageStory): Observable<EntityResponseType> {
    return this.http.put<IMStageStory>(this.resourceUrl, mStageStory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMStageStory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMStageStory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
