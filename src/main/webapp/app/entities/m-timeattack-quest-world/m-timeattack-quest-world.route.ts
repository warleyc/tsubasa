import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';
import { MTimeattackQuestWorldService } from './m-timeattack-quest-world.service';
import { MTimeattackQuestWorldComponent } from './m-timeattack-quest-world.component';
import { MTimeattackQuestWorldDetailComponent } from './m-timeattack-quest-world-detail.component';
import { MTimeattackQuestWorldUpdateComponent } from './m-timeattack-quest-world-update.component';
import { MTimeattackQuestWorldDeletePopupComponent } from './m-timeattack-quest-world-delete-dialog.component';
import { IMTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestWorldResolve implements Resolve<IMTimeattackQuestWorld> {
  constructor(private service: MTimeattackQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTimeattackQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTimeattackQuestWorld>) => response.ok),
        map((mTimeattackQuestWorld: HttpResponse<MTimeattackQuestWorld>) => mTimeattackQuestWorld.body)
      );
    }
    return of(new MTimeattackQuestWorld());
  }
}

export const mTimeattackQuestWorldRoute: Routes = [
  {
    path: '',
    component: MTimeattackQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTimeattackQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTimeattackQuestWorldDetailComponent,
    resolve: {
      mTimeattackQuestWorld: MTimeattackQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTimeattackQuestWorldUpdateComponent,
    resolve: {
      mTimeattackQuestWorld: MTimeattackQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTimeattackQuestWorldUpdateComponent,
    resolve: {
      mTimeattackQuestWorld: MTimeattackQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTimeattackQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTimeattackQuestWorldDeletePopupComponent,
    resolve: {
      mTimeattackQuestWorld: MTimeattackQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
