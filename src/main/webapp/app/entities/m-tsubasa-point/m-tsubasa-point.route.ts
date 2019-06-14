import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';
import { MTsubasaPointService } from './m-tsubasa-point.service';
import { MTsubasaPointComponent } from './m-tsubasa-point.component';
import { MTsubasaPointDetailComponent } from './m-tsubasa-point-detail.component';
import { MTsubasaPointUpdateComponent } from './m-tsubasa-point-update.component';
import { MTsubasaPointDeletePopupComponent } from './m-tsubasa-point-delete-dialog.component';
import { IMTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';

@Injectable({ providedIn: 'root' })
export class MTsubasaPointResolve implements Resolve<IMTsubasaPoint> {
  constructor(private service: MTsubasaPointService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTsubasaPoint> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTsubasaPoint>) => response.ok),
        map((mTsubasaPoint: HttpResponse<MTsubasaPoint>) => mTsubasaPoint.body)
      );
    }
    return of(new MTsubasaPoint());
  }
}

export const mTsubasaPointRoute: Routes = [
  {
    path: '',
    component: MTsubasaPointComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTsubasaPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTsubasaPointDetailComponent,
    resolve: {
      mTsubasaPoint: MTsubasaPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTsubasaPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTsubasaPointUpdateComponent,
    resolve: {
      mTsubasaPoint: MTsubasaPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTsubasaPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTsubasaPointUpdateComponent,
    resolve: {
      mTsubasaPoint: MTsubasaPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTsubasaPoints'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTsubasaPointPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTsubasaPointDeletePopupComponent,
    resolve: {
      mTsubasaPoint: MTsubasaPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTsubasaPoints'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
