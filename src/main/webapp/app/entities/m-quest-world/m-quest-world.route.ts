import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestWorld } from 'app/shared/model/m-quest-world.model';
import { MQuestWorldService } from './m-quest-world.service';
import { MQuestWorldComponent } from './m-quest-world.component';
import { MQuestWorldDetailComponent } from './m-quest-world-detail.component';
import { MQuestWorldUpdateComponent } from './m-quest-world-update.component';
import { MQuestWorldDeletePopupComponent } from './m-quest-world-delete-dialog.component';
import { IMQuestWorld } from 'app/shared/model/m-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MQuestWorldResolve implements Resolve<IMQuestWorld> {
  constructor(private service: MQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestWorld>) => response.ok),
        map((mQuestWorld: HttpResponse<MQuestWorld>) => mQuestWorld.body)
      );
    }
    return of(new MQuestWorld());
  }
}

export const mQuestWorldRoute: Routes = [
  {
    path: '',
    component: MQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestWorldDetailComponent,
    resolve: {
      mQuestWorld: MQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestWorldUpdateComponent,
    resolve: {
      mQuestWorld: MQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestWorldUpdateComponent,
    resolve: {
      mQuestWorld: MQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestWorldDeletePopupComponent,
    resolve: {
      mQuestWorld: MQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
