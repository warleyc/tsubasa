import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';
import { MAdventQuestWorldService } from './m-advent-quest-world.service';
import { MAdventQuestWorldComponent } from './m-advent-quest-world.component';
import { MAdventQuestWorldDetailComponent } from './m-advent-quest-world-detail.component';
import { MAdventQuestWorldUpdateComponent } from './m-advent-quest-world-update.component';
import { MAdventQuestWorldDeletePopupComponent } from './m-advent-quest-world-delete-dialog.component';
import { IMAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MAdventQuestWorldResolve implements Resolve<IMAdventQuestWorld> {
  constructor(private service: MAdventQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAdventQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAdventQuestWorld>) => response.ok),
        map((mAdventQuestWorld: HttpResponse<MAdventQuestWorld>) => mAdventQuestWorld.body)
      );
    }
    return of(new MAdventQuestWorld());
  }
}

export const mAdventQuestWorldRoute: Routes = [
  {
    path: '',
    component: MAdventQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAdventQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAdventQuestWorldDetailComponent,
    resolve: {
      mAdventQuestWorld: MAdventQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAdventQuestWorldUpdateComponent,
    resolve: {
      mAdventQuestWorld: MAdventQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAdventQuestWorldUpdateComponent,
    resolve: {
      mAdventQuestWorld: MAdventQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAdventQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAdventQuestWorldDeletePopupComponent,
    resolve: {
      mAdventQuestWorld: MAdventQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
