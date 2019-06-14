import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';
import { MWeeklyQuestStageService } from './m-weekly-quest-stage.service';
import { MWeeklyQuestStageComponent } from './m-weekly-quest-stage.component';
import { MWeeklyQuestStageDetailComponent } from './m-weekly-quest-stage-detail.component';
import { MWeeklyQuestStageUpdateComponent } from './m-weekly-quest-stage-update.component';
import { MWeeklyQuestStageDeletePopupComponent } from './m-weekly-quest-stage-delete-dialog.component';
import { IMWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestStageResolve implements Resolve<IMWeeklyQuestStage> {
  constructor(private service: MWeeklyQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMWeeklyQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MWeeklyQuestStage>) => response.ok),
        map((mWeeklyQuestStage: HttpResponse<MWeeklyQuestStage>) => mWeeklyQuestStage.body)
      );
    }
    return of(new MWeeklyQuestStage());
  }
}

export const mWeeklyQuestStageRoute: Routes = [
  {
    path: '',
    component: MWeeklyQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MWeeklyQuestStageDetailComponent,
    resolve: {
      mWeeklyQuestStage: MWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MWeeklyQuestStageUpdateComponent,
    resolve: {
      mWeeklyQuestStage: MWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MWeeklyQuestStageUpdateComponent,
    resolve: {
      mWeeklyQuestStage: MWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mWeeklyQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MWeeklyQuestStageDeletePopupComponent,
    resolve: {
      mWeeklyQuestStage: MWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
