import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMatchOption } from 'app/shared/model/m-match-option.model';
import { MMatchOptionService } from './m-match-option.service';
import { MMatchOptionComponent } from './m-match-option.component';
import { MMatchOptionDetailComponent } from './m-match-option-detail.component';
import { MMatchOptionUpdateComponent } from './m-match-option-update.component';
import { MMatchOptionDeletePopupComponent } from './m-match-option-delete-dialog.component';
import { IMMatchOption } from 'app/shared/model/m-match-option.model';

@Injectable({ providedIn: 'root' })
export class MMatchOptionResolve implements Resolve<IMMatchOption> {
  constructor(private service: MMatchOptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMatchOption> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMatchOption>) => response.ok),
        map((mMatchOption: HttpResponse<MMatchOption>) => mMatchOption.body)
      );
    }
    return of(new MMatchOption());
  }
}

export const mMatchOptionRoute: Routes = [
  {
    path: '',
    component: MMatchOptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMatchOptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMatchOptionDetailComponent,
    resolve: {
      mMatchOption: MMatchOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchOptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMatchOptionUpdateComponent,
    resolve: {
      mMatchOption: MMatchOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchOptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMatchOptionUpdateComponent,
    resolve: {
      mMatchOption: MMatchOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchOptions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMatchOptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMatchOptionDeletePopupComponent,
    resolve: {
      mMatchOption: MMatchOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchOptions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
