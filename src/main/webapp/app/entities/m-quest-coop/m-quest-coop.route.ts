import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestCoop } from 'app/shared/model/m-quest-coop.model';
import { MQuestCoopService } from './m-quest-coop.service';
import { MQuestCoopComponent } from './m-quest-coop.component';
import { MQuestCoopDetailComponent } from './m-quest-coop-detail.component';
import { MQuestCoopUpdateComponent } from './m-quest-coop-update.component';
import { MQuestCoopDeletePopupComponent } from './m-quest-coop-delete-dialog.component';
import { IMQuestCoop } from 'app/shared/model/m-quest-coop.model';

@Injectable({ providedIn: 'root' })
export class MQuestCoopResolve implements Resolve<IMQuestCoop> {
  constructor(private service: MQuestCoopService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestCoop> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestCoop>) => response.ok),
        map((mQuestCoop: HttpResponse<MQuestCoop>) => mQuestCoop.body)
      );
    }
    return of(new MQuestCoop());
  }
}

export const mQuestCoopRoute: Routes = [
  {
    path: '',
    component: MQuestCoopComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestCoops'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestCoopDetailComponent,
    resolve: {
      mQuestCoop: MQuestCoopResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoops'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestCoopUpdateComponent,
    resolve: {
      mQuestCoop: MQuestCoopResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoops'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestCoopUpdateComponent,
    resolve: {
      mQuestCoop: MQuestCoopResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoops'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestCoopPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestCoopDeletePopupComponent,
    resolve: {
      mQuestCoop: MQuestCoopResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoops'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
