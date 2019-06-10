import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDefine } from 'app/shared/model/m-define.model';
import { MDefineService } from './m-define.service';
import { MDefineComponent } from './m-define.component';
import { MDefineDetailComponent } from './m-define-detail.component';
import { MDefineUpdateComponent } from './m-define-update.component';
import { MDefineDeletePopupComponent } from './m-define-delete-dialog.component';
import { IMDefine } from 'app/shared/model/m-define.model';

@Injectable({ providedIn: 'root' })
export class MDefineResolve implements Resolve<IMDefine> {
  constructor(private service: MDefineService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDefine> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDefine>) => response.ok),
        map((mDefine: HttpResponse<MDefine>) => mDefine.body)
      );
    }
    return of(new MDefine());
  }
}

export const mDefineRoute: Routes = [
  {
    path: '',
    component: MDefineComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDefines'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDefineDetailComponent,
    resolve: {
      mDefine: MDefineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDefines'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDefineUpdateComponent,
    resolve: {
      mDefine: MDefineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDefines'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDefineUpdateComponent,
    resolve: {
      mDefine: MDefineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDefines'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDefinePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDefineDeletePopupComponent,
    resolve: {
      mDefine: MDefineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDefines'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
