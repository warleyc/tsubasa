import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestStage } from 'app/shared/model/m-quest-stage.model';
import { MQuestStageService } from './m-quest-stage.service';
import { MQuestStageComponent } from './m-quest-stage.component';
import { MQuestStageDetailComponent } from './m-quest-stage-detail.component';
import { MQuestStageUpdateComponent } from './m-quest-stage-update.component';
import { MQuestStageDeletePopupComponent } from './m-quest-stage-delete-dialog.component';
import { IMQuestStage } from 'app/shared/model/m-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MQuestStageResolve implements Resolve<IMQuestStage> {
  constructor(private service: MQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestStage>) => response.ok),
        map((mQuestStage: HttpResponse<MQuestStage>) => mQuestStage.body)
      );
    }
    return of(new MQuestStage());
  }
}

export const mQuestStageRoute: Routes = [
  {
    path: '',
    component: MQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestStageDetailComponent,
    resolve: {
      mQuestStage: MQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestStageUpdateComponent,
    resolve: {
      mQuestStage: MQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestStageUpdateComponent,
    resolve: {
      mQuestStage: MQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestStageDeletePopupComponent,
    resolve: {
      mQuestStage: MQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
