import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';
import { MMarathonQuestWorldService } from './m-marathon-quest-world.service';
import { MMarathonQuestWorldComponent } from './m-marathon-quest-world.component';
import { MMarathonQuestWorldDetailComponent } from './m-marathon-quest-world-detail.component';
import { MMarathonQuestWorldUpdateComponent } from './m-marathon-quest-world-update.component';
import { MMarathonQuestWorldDeletePopupComponent } from './m-marathon-quest-world-delete-dialog.component';
import { IMMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MMarathonQuestWorldResolve implements Resolve<IMMarathonQuestWorld> {
  constructor(private service: MMarathonQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonQuestWorld>) => response.ok),
        map((mMarathonQuestWorld: HttpResponse<MMarathonQuestWorld>) => mMarathonQuestWorld.body)
      );
    }
    return of(new MMarathonQuestWorld());
  }
}

export const mMarathonQuestWorldRoute: Routes = [
  {
    path: '',
    component: MMarathonQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonQuestWorldDetailComponent,
    resolve: {
      mMarathonQuestWorld: MMarathonQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonQuestWorldUpdateComponent,
    resolve: {
      mMarathonQuestWorld: MMarathonQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonQuestWorldUpdateComponent,
    resolve: {
      mMarathonQuestWorld: MMarathonQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonQuestWorldDeletePopupComponent,
    resolve: {
      mMarathonQuestWorld: MMarathonQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
