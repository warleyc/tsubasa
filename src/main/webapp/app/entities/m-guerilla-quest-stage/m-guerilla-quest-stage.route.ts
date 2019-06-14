import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';
import { MGuerillaQuestStageService } from './m-guerilla-quest-stage.service';
import { MGuerillaQuestStageComponent } from './m-guerilla-quest-stage.component';
import { MGuerillaQuestStageDetailComponent } from './m-guerilla-quest-stage-detail.component';
import { MGuerillaQuestStageUpdateComponent } from './m-guerilla-quest-stage-update.component';
import { MGuerillaQuestStageDeletePopupComponent } from './m-guerilla-quest-stage-delete-dialog.component';
import { IMGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestStageResolve implements Resolve<IMGuerillaQuestStage> {
  constructor(private service: MGuerillaQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuerillaQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuerillaQuestStage>) => response.ok),
        map((mGuerillaQuestStage: HttpResponse<MGuerillaQuestStage>) => mGuerillaQuestStage.body)
      );
    }
    return of(new MGuerillaQuestStage());
  }
}

export const mGuerillaQuestStageRoute: Routes = [
  {
    path: '',
    component: MGuerillaQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuerillaQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuerillaQuestStageDetailComponent,
    resolve: {
      mGuerillaQuestStage: MGuerillaQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuerillaQuestStageUpdateComponent,
    resolve: {
      mGuerillaQuestStage: MGuerillaQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuerillaQuestStageUpdateComponent,
    resolve: {
      mGuerillaQuestStage: MGuerillaQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuerillaQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuerillaQuestStageDeletePopupComponent,
    resolve: {
      mGuerillaQuestStage: MGuerillaQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
