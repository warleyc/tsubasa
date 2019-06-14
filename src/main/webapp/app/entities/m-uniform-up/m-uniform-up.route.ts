import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MUniformUp } from 'app/shared/model/m-uniform-up.model';
import { MUniformUpService } from './m-uniform-up.service';
import { MUniformUpComponent } from './m-uniform-up.component';
import { MUniformUpDetailComponent } from './m-uniform-up-detail.component';
import { MUniformUpUpdateComponent } from './m-uniform-up-update.component';
import { MUniformUpDeletePopupComponent } from './m-uniform-up-delete-dialog.component';
import { IMUniformUp } from 'app/shared/model/m-uniform-up.model';

@Injectable({ providedIn: 'root' })
export class MUniformUpResolve implements Resolve<IMUniformUp> {
  constructor(private service: MUniformUpService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMUniformUp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MUniformUp>) => response.ok),
        map((mUniformUp: HttpResponse<MUniformUp>) => mUniformUp.body)
      );
    }
    return of(new MUniformUp());
  }
}

export const mUniformUpRoute: Routes = [
  {
    path: '',
    component: MUniformUpComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MUniformUpDetailComponent,
    resolve: {
      mUniformUp: MUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MUniformUpUpdateComponent,
    resolve: {
      mUniformUp: MUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MUniformUpUpdateComponent,
    resolve: {
      mUniformUp: MUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformUps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mUniformUpPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MUniformUpDeletePopupComponent,
    resolve: {
      mUniformUp: MUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformUps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
