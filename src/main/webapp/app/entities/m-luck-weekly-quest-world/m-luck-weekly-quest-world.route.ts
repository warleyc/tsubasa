import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';
import { MLuckWeeklyQuestWorldService } from './m-luck-weekly-quest-world.service';
import { MLuckWeeklyQuestWorldComponent } from './m-luck-weekly-quest-world.component';
import { MLuckWeeklyQuestWorldDetailComponent } from './m-luck-weekly-quest-world-detail.component';
import { MLuckWeeklyQuestWorldUpdateComponent } from './m-luck-weekly-quest-world-update.component';
import { MLuckWeeklyQuestWorldDeletePopupComponent } from './m-luck-weekly-quest-world-delete-dialog.component';
import { IMLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestWorldResolve implements Resolve<IMLuckWeeklyQuestWorld> {
  constructor(private service: MLuckWeeklyQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLuckWeeklyQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLuckWeeklyQuestWorld>) => response.ok),
        map((mLuckWeeklyQuestWorld: HttpResponse<MLuckWeeklyQuestWorld>) => mLuckWeeklyQuestWorld.body)
      );
    }
    return of(new MLuckWeeklyQuestWorld());
  }
}

export const mLuckWeeklyQuestWorldRoute: Routes = [
  {
    path: '',
    component: MLuckWeeklyQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLuckWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLuckWeeklyQuestWorldDetailComponent,
    resolve: {
      mLuckWeeklyQuestWorld: MLuckWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLuckWeeklyQuestWorldUpdateComponent,
    resolve: {
      mLuckWeeklyQuestWorld: MLuckWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLuckWeeklyQuestWorldUpdateComponent,
    resolve: {
      mLuckWeeklyQuestWorld: MLuckWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLuckWeeklyQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLuckWeeklyQuestWorldDeletePopupComponent,
    resolve: {
      mLuckWeeklyQuestWorld: MLuckWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
