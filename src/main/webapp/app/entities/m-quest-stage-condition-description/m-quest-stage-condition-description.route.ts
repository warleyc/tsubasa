import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';
import { MQuestStageConditionDescriptionService } from './m-quest-stage-condition-description.service';
import { MQuestStageConditionDescriptionComponent } from './m-quest-stage-condition-description.component';
import { MQuestStageConditionDescriptionDetailComponent } from './m-quest-stage-condition-description-detail.component';
import { MQuestStageConditionDescriptionUpdateComponent } from './m-quest-stage-condition-description-update.component';
import { MQuestStageConditionDescriptionDeletePopupComponent } from './m-quest-stage-condition-description-delete-dialog.component';
import { IMQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';

@Injectable({ providedIn: 'root' })
export class MQuestStageConditionDescriptionResolve implements Resolve<IMQuestStageConditionDescription> {
  constructor(private service: MQuestStageConditionDescriptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestStageConditionDescription> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestStageConditionDescription>) => response.ok),
        map((mQuestStageConditionDescription: HttpResponse<MQuestStageConditionDescription>) => mQuestStageConditionDescription.body)
      );
    }
    return of(new MQuestStageConditionDescription());
  }
}

export const mQuestStageConditionDescriptionRoute: Routes = [
  {
    path: '',
    component: MQuestStageConditionDescriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestStageConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestStageConditionDescriptionDetailComponent,
    resolve: {
      mQuestStageConditionDescription: MQuestStageConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestStageConditionDescriptionUpdateComponent,
    resolve: {
      mQuestStageConditionDescription: MQuestStageConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestStageConditionDescriptionUpdateComponent,
    resolve: {
      mQuestStageConditionDescription: MQuestStageConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestStageConditionDescriptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestStageConditionDescriptionDeletePopupComponent,
    resolve: {
      mQuestStageConditionDescription: MQuestStageConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditionDescriptions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
