import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MNationality } from 'app/shared/model/m-nationality.model';
import { MNationalityService } from './m-nationality.service';
import { MNationalityComponent } from './m-nationality.component';
import { MNationalityDetailComponent } from './m-nationality-detail.component';
import { MNationalityUpdateComponent } from './m-nationality-update.component';
import { MNationalityDeletePopupComponent } from './m-nationality-delete-dialog.component';
import { IMNationality } from 'app/shared/model/m-nationality.model';

@Injectable({ providedIn: 'root' })
export class MNationalityResolve implements Resolve<IMNationality> {
  constructor(private service: MNationalityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMNationality> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MNationality>) => response.ok),
        map((mNationality: HttpResponse<MNationality>) => mNationality.body)
      );
    }
    return of(new MNationality());
  }
}

export const mNationalityRoute: Routes = [
  {
    path: '',
    component: MNationalityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MNationalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MNationalityDetailComponent,
    resolve: {
      mNationality: MNationalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNationalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MNationalityUpdateComponent,
    resolve: {
      mNationality: MNationalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNationalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MNationalityUpdateComponent,
    resolve: {
      mNationality: MNationalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNationalities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mNationalityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MNationalityDeletePopupComponent,
    resolve: {
      mNationality: MNationalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNationalities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
