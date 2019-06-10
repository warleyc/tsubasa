import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCut } from 'app/shared/model/m-cut.model';
import { MCutService } from './m-cut.service';
import { MCutComponent } from './m-cut.component';
import { MCutDetailComponent } from './m-cut-detail.component';
import { MCutUpdateComponent } from './m-cut-update.component';
import { MCutDeletePopupComponent } from './m-cut-delete-dialog.component';
import { IMCut } from 'app/shared/model/m-cut.model';

@Injectable({ providedIn: 'root' })
export class MCutResolve implements Resolve<IMCut> {
  constructor(private service: MCutService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCut> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCut>) => response.ok),
        map((mCut: HttpResponse<MCut>) => mCut.body)
      );
    }
    return of(new MCut());
  }
}

export const mCutRoute: Routes = [
  {
    path: '',
    component: MCutComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCutDetailComponent,
    resolve: {
      mCut: MCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCutUpdateComponent,
    resolve: {
      mCut: MCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCutUpdateComponent,
    resolve: {
      mCut: MCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCuts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCutPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCutDeletePopupComponent,
    resolve: {
      mCut: MCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCuts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
