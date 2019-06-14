import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';
import { MLoginBonusSerifService } from './m-login-bonus-serif.service';
import { MLoginBonusSerifComponent } from './m-login-bonus-serif.component';
import { MLoginBonusSerifDetailComponent } from './m-login-bonus-serif-detail.component';
import { MLoginBonusSerifUpdateComponent } from './m-login-bonus-serif-update.component';
import { MLoginBonusSerifDeletePopupComponent } from './m-login-bonus-serif-delete-dialog.component';
import { IMLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';

@Injectable({ providedIn: 'root' })
export class MLoginBonusSerifResolve implements Resolve<IMLoginBonusSerif> {
  constructor(private service: MLoginBonusSerifService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLoginBonusSerif> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLoginBonusSerif>) => response.ok),
        map((mLoginBonusSerif: HttpResponse<MLoginBonusSerif>) => mLoginBonusSerif.body)
      );
    }
    return of(new MLoginBonusSerif());
  }
}

export const mLoginBonusSerifRoute: Routes = [
  {
    path: '',
    component: MLoginBonusSerifComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLoginBonusSerifs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLoginBonusSerifDetailComponent,
    resolve: {
      mLoginBonusSerif: MLoginBonusSerifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusSerifs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLoginBonusSerifUpdateComponent,
    resolve: {
      mLoginBonusSerif: MLoginBonusSerifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusSerifs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLoginBonusSerifUpdateComponent,
    resolve: {
      mLoginBonusSerif: MLoginBonusSerifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusSerifs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLoginBonusSerifPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLoginBonusSerifDeletePopupComponent,
    resolve: {
      mLoginBonusSerif: MLoginBonusSerifResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusSerifs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
