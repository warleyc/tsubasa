import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';
import { MGuerillaQuestWorldService } from './m-guerilla-quest-world.service';
import { MGuerillaQuestWorldComponent } from './m-guerilla-quest-world.component';
import { MGuerillaQuestWorldDetailComponent } from './m-guerilla-quest-world-detail.component';
import { MGuerillaQuestWorldUpdateComponent } from './m-guerilla-quest-world-update.component';
import { MGuerillaQuestWorldDeletePopupComponent } from './m-guerilla-quest-world-delete-dialog.component';
import { IMGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestWorldResolve implements Resolve<IMGuerillaQuestWorld> {
  constructor(private service: MGuerillaQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuerillaQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuerillaQuestWorld>) => response.ok),
        map((mGuerillaQuestWorld: HttpResponse<MGuerillaQuestWorld>) => mGuerillaQuestWorld.body)
      );
    }
    return of(new MGuerillaQuestWorld());
  }
}

export const mGuerillaQuestWorldRoute: Routes = [
  {
    path: '',
    component: MGuerillaQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuerillaQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuerillaQuestWorldDetailComponent,
    resolve: {
      mGuerillaQuestWorld: MGuerillaQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuerillaQuestWorldUpdateComponent,
    resolve: {
      mGuerillaQuestWorld: MGuerillaQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuerillaQuestWorldUpdateComponent,
    resolve: {
      mGuerillaQuestWorld: MGuerillaQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuerillaQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuerillaQuestWorldDeletePopupComponent,
    resolve: {
      mGuerillaQuestWorld: MGuerillaQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
