import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

type EntityResponseType = HttpResponse<IMTargetCharacterGroup>;
type EntityArrayResponseType = HttpResponse<IMTargetCharacterGroup[]>;

@Injectable({ providedIn: 'root' })
export class MTargetCharacterGroupService {
  public resourceUrl = SERVER_API_URL + 'api/m-target-character-groups';

  constructor(protected http: HttpClient) {}

  create(mTargetCharacterGroup: IMTargetCharacterGroup): Observable<EntityResponseType> {
    return this.http.post<IMTargetCharacterGroup>(this.resourceUrl, mTargetCharacterGroup, { observe: 'response' });
  }

  update(mTargetCharacterGroup: IMTargetCharacterGroup): Observable<EntityResponseType> {
    return this.http.put<IMTargetCharacterGroup>(this.resourceUrl, mTargetCharacterGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMTargetCharacterGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMTargetCharacterGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
