import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';
import { MTimeattackQuestStageService } from './m-timeattack-quest-stage.service';
import { MTimeattackQuestStageComponent } from './m-timeattack-quest-stage.component';
import { MTimeattackQuestStageDetailComponent } from './m-timeattack-quest-stage-detail.component';
import { MTimeattackQuestStageUpdateComponent } from './m-timeattack-quest-stage-update.component';
import { MTimeattackQuestStageDeletePopupComponent } from './m-timeattack-quest-stage-delete-dialog.component';
import { IMTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestStageResolve implements Resolve<IMTimeattackQuestStage> {
  constructor(private service: MTimeattackQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTimeattackQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTimeattackQuestStage>) => response.ok),
        map((mTimeattackQuestStage: HttpResponse<MTimeattackQuestStage>) => mTimeattackQuestStage.body)
      );
    }
    return of(new MTimeattackQuestStage());
  }
}

export const mTimeattackQuestStageRoute: Routes = [
  {
    path: '',
    component: MTimeattackQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTimeattackQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTimeattackQuestStageDetailComponent,
    resolve: {
      mTimeattackQuestStage: MTimeattackQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTimeattackQuestStageUpdateComponent,
    resolve: {
      mTimeattackQuestStage: MTimeattackQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTimeattackQuestStageUpdateComponent,
    resolve: {
      mTimeattackQuestStage: MTimeattackQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTimeattackQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTimeattackQuestStageDeletePopupComponent,
    resolve: {
      mTimeattackQuestStage: MTimeattackQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
