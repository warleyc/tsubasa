import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMedal } from 'app/shared/model/m-medal.model';
import { MMedalService } from './m-medal.service';
import { MMedalComponent } from './m-medal.component';
import { MMedalDetailComponent } from './m-medal-detail.component';
import { MMedalUpdateComponent } from './m-medal-update.component';
import { MMedalDeletePopupComponent } from './m-medal-delete-dialog.component';
import { IMMedal } from 'app/shared/model/m-medal.model';

@Injectable({ providedIn: 'root' })
export class MMedalResolve implements Resolve<IMMedal> {
  constructor(private service: MMedalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMedal> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMedal>) => response.ok),
        map((mMedal: HttpResponse<MMedal>) => mMedal.body)
      );
    }
    return of(new MMedal());
  }
}

export const mMedalRoute: Routes = [
  {
    path: '',
    component: MMedalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMedalDetailComponent,
    resolve: {
      mMedal: MMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMedalUpdateComponent,
    resolve: {
      mMedal: MMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMedalUpdateComponent,
    resolve: {
      mMedal: MMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMedals'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMedalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMedalDeletePopupComponent,
    resolve: {
      mMedal: MMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMedals'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
