import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';
import { MModelQuestStageService } from './m-model-quest-stage.service';
import { MModelQuestStageComponent } from './m-model-quest-stage.component';
import { MModelQuestStageDetailComponent } from './m-model-quest-stage-detail.component';
import { MModelQuestStageUpdateComponent } from './m-model-quest-stage-update.component';
import { MModelQuestStageDeletePopupComponent } from './m-model-quest-stage-delete-dialog.component';
import { IMModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MModelQuestStageResolve implements Resolve<IMModelQuestStage> {
  constructor(private service: MModelQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelQuestStage>) => response.ok),
        map((mModelQuestStage: HttpResponse<MModelQuestStage>) => mModelQuestStage.body)
      );
    }
    return of(new MModelQuestStage());
  }
}

export const mModelQuestStageRoute: Routes = [
  {
    path: '',
    component: MModelQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelQuestStageDetailComponent,
    resolve: {
      mModelQuestStage: MModelQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelQuestStageUpdateComponent,
    resolve: {
      mModelQuestStage: MModelQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelQuestStageUpdateComponent,
    resolve: {
      mModelQuestStage: MModelQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelQuestStageDeletePopupComponent,
    resolve: {
      mModelQuestStage: MModelQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
