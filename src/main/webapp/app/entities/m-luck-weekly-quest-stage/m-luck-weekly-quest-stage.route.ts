import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';
import { MLuckWeeklyQuestStageService } from './m-luck-weekly-quest-stage.service';
import { MLuckWeeklyQuestStageComponent } from './m-luck-weekly-quest-stage.component';
import { MLuckWeeklyQuestStageDetailComponent } from './m-luck-weekly-quest-stage-detail.component';
import { MLuckWeeklyQuestStageUpdateComponent } from './m-luck-weekly-quest-stage-update.component';
import { MLuckWeeklyQuestStageDeletePopupComponent } from './m-luck-weekly-quest-stage-delete-dialog.component';
import { IMLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestStageResolve implements Resolve<IMLuckWeeklyQuestStage> {
  constructor(private service: MLuckWeeklyQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLuckWeeklyQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLuckWeeklyQuestStage>) => response.ok),
        map((mLuckWeeklyQuestStage: HttpResponse<MLuckWeeklyQuestStage>) => mLuckWeeklyQuestStage.body)
      );
    }
    return of(new MLuckWeeklyQuestStage());
  }
}

export const mLuckWeeklyQuestStageRoute: Routes = [
  {
    path: '',
    component: MLuckWeeklyQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLuckWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLuckWeeklyQuestStageDetailComponent,
    resolve: {
      mLuckWeeklyQuestStage: MLuckWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLuckWeeklyQuestStageUpdateComponent,
    resolve: {
      mLuckWeeklyQuestStage: MLuckWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLuckWeeklyQuestStageUpdateComponent,
    resolve: {
      mLuckWeeklyQuestStage: MLuckWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLuckWeeklyQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLuckWeeklyQuestStageDeletePopupComponent,
    resolve: {
      mLuckWeeklyQuestStage: MLuckWeeklyQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
