import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MFullPowerPoint } from 'app/shared/model/m-full-power-point.model';
import { MFullPowerPointService } from './m-full-power-point.service';
import { MFullPowerPointComponent } from './m-full-power-point.component';
import { MFullPowerPointDetailComponent } from './m-full-power-point-detail.component';
import { MFullPowerPointUpdateComponent } from './m-full-power-point-update.component';
import { MFullPowerPointDeletePopupComponent } from './m-full-power-point-delete-dialog.component';
import { IMFullPowerPoint } from 'app/shared/model/m-full-power-point.model';

@Injectable({ providedIn: 'root' })
export class MFullPowerPointResolve implements Resolve<IMFullPowerPoint> {
  constructor(private service: MFullPowerPointService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMFullPowerPoint> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MFullPowerPoint>) => response.ok),
        map((mFullPowerPoint: HttpResponse<MFullPowerPoint>) => mFullPowerPoint.body)
      );
    }
    return of(new MFullPowerPoint());
  }
}

export const mFullPowerPointRoute: Routes = [
  {
    path: '',
    component: MFullPowerPointComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MFullPowerPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MFullPowerPointDetailComponent,
    resolve: {
      mFullPowerPoint: MFullPowerPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFullPowerPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MFullPowerPointUpdateComponent,
    resolve: {
      mFullPowerPoint: MFullPowerPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFullPowerPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MFullPowerPointUpdateComponent,
    resolve: {
      mFullPowerPoint: MFullPowerPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFullPowerPoints'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mFullPowerPointPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MFullPowerPointDeletePopupComponent,
    resolve: {
      mFullPowerPoint: MFullPowerPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFullPowerPoints'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
