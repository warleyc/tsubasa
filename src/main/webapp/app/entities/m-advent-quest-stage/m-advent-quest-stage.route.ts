import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';
import { MAdventQuestStageService } from './m-advent-quest-stage.service';
import { MAdventQuestStageComponent } from './m-advent-quest-stage.component';
import { MAdventQuestStageDetailComponent } from './m-advent-quest-stage-detail.component';
import { MAdventQuestStageUpdateComponent } from './m-advent-quest-stage-update.component';
import { MAdventQuestStageDeletePopupComponent } from './m-advent-quest-stage-delete-dialog.component';
import { IMAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MAdventQuestStageResolve implements Resolve<IMAdventQuestStage> {
  constructor(private service: MAdventQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAdventQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAdventQuestStage>) => response.ok),
        map((mAdventQuestStage: HttpResponse<MAdventQuestStage>) => mAdventQuestStage.body)
      );
    }
    return of(new MAdventQuestStage());
  }
}

export const mAdventQuestStageRoute: Routes = [
  {
    path: '',
    component: MAdventQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAdventQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAdventQuestStageDetailComponent,
    resolve: {
      mAdventQuestStage: MAdventQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAdventQuestStageUpdateComponent,
    resolve: {
      mAdventQuestStage: MAdventQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAdventQuestStageUpdateComponent,
    resolve: {
      mAdventQuestStage: MAdventQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAdventQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAdventQuestStageDeletePopupComponent,
    resolve: {
      mAdventQuestStage: MAdventQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
