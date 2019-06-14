import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';
import { MWeeklyQuestWorldService } from './m-weekly-quest-world.service';
import { MWeeklyQuestWorldComponent } from './m-weekly-quest-world.component';
import { MWeeklyQuestWorldDetailComponent } from './m-weekly-quest-world-detail.component';
import { MWeeklyQuestWorldUpdateComponent } from './m-weekly-quest-world-update.component';
import { MWeeklyQuestWorldDeletePopupComponent } from './m-weekly-quest-world-delete-dialog.component';
import { IMWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestWorldResolve implements Resolve<IMWeeklyQuestWorld> {
  constructor(private service: MWeeklyQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMWeeklyQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MWeeklyQuestWorld>) => response.ok),
        map((mWeeklyQuestWorld: HttpResponse<MWeeklyQuestWorld>) => mWeeklyQuestWorld.body)
      );
    }
    return of(new MWeeklyQuestWorld());
  }
}

export const mWeeklyQuestWorldRoute: Routes = [
  {
    path: '',
    component: MWeeklyQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MWeeklyQuestWorldDetailComponent,
    resolve: {
      mWeeklyQuestWorld: MWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MWeeklyQuestWorldUpdateComponent,
    resolve: {
      mWeeklyQuestWorld: MWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MWeeklyQuestWorldUpdateComponent,
    resolve: {
      mWeeklyQuestWorld: MWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mWeeklyQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MWeeklyQuestWorldDeletePopupComponent,
    resolve: {
      mWeeklyQuestWorld: MWeeklyQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
