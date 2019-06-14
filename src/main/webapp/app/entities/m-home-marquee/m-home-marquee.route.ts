import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MHomeMarquee } from 'app/shared/model/m-home-marquee.model';
import { MHomeMarqueeService } from './m-home-marquee.service';
import { MHomeMarqueeComponent } from './m-home-marquee.component';
import { MHomeMarqueeDetailComponent } from './m-home-marquee-detail.component';
import { MHomeMarqueeUpdateComponent } from './m-home-marquee-update.component';
import { MHomeMarqueeDeletePopupComponent } from './m-home-marquee-delete-dialog.component';
import { IMHomeMarquee } from 'app/shared/model/m-home-marquee.model';

@Injectable({ providedIn: 'root' })
export class MHomeMarqueeResolve implements Resolve<IMHomeMarquee> {
  constructor(private service: MHomeMarqueeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMHomeMarquee> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MHomeMarquee>) => response.ok),
        map((mHomeMarquee: HttpResponse<MHomeMarquee>) => mHomeMarquee.body)
      );
    }
    return of(new MHomeMarquee());
  }
}

export const mHomeMarqueeRoute: Routes = [
  {
    path: '',
    component: MHomeMarqueeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MHomeMarquees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MHomeMarqueeDetailComponent,
    resolve: {
      mHomeMarquee: MHomeMarqueeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeMarquees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MHomeMarqueeUpdateComponent,
    resolve: {
      mHomeMarquee: MHomeMarqueeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeMarquees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MHomeMarqueeUpdateComponent,
    resolve: {
      mHomeMarquee: MHomeMarqueeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeMarquees'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mHomeMarqueePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MHomeMarqueeDeletePopupComponent,
    resolve: {
      mHomeMarquee: MHomeMarqueeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeMarquees'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
