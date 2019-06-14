import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';
import { MMarathonQuestStageService } from './m-marathon-quest-stage.service';
import { MMarathonQuestStageComponent } from './m-marathon-quest-stage.component';
import { MMarathonQuestStageDetailComponent } from './m-marathon-quest-stage-detail.component';
import { MMarathonQuestStageUpdateComponent } from './m-marathon-quest-stage-update.component';
import { MMarathonQuestStageDeletePopupComponent } from './m-marathon-quest-stage-delete-dialog.component';
import { IMMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MMarathonQuestStageResolve implements Resolve<IMMarathonQuestStage> {
  constructor(private service: MMarathonQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonQuestStage>) => response.ok),
        map((mMarathonQuestStage: HttpResponse<MMarathonQuestStage>) => mMarathonQuestStage.body)
      );
    }
    return of(new MMarathonQuestStage());
  }
}

export const mMarathonQuestStageRoute: Routes = [
  {
    path: '',
    component: MMarathonQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonQuestStageDetailComponent,
    resolve: {
      mMarathonQuestStage: MMarathonQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonQuestStageUpdateComponent,
    resolve: {
      mMarathonQuestStage: MMarathonQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonQuestStageUpdateComponent,
    resolve: {
      mMarathonQuestStage: MMarathonQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonQuestStageDeletePopupComponent,
    resolve: {
      mMarathonQuestStage: MMarathonQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
