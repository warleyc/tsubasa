import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpWave } from 'app/shared/model/m-pvp-wave.model';
import { MPvpWaveService } from './m-pvp-wave.service';
import { MPvpWaveComponent } from './m-pvp-wave.component';
import { MPvpWaveDetailComponent } from './m-pvp-wave-detail.component';
import { MPvpWaveUpdateComponent } from './m-pvp-wave-update.component';
import { MPvpWaveDeletePopupComponent } from './m-pvp-wave-delete-dialog.component';
import { IMPvpWave } from 'app/shared/model/m-pvp-wave.model';

@Injectable({ providedIn: 'root' })
export class MPvpWaveResolve implements Resolve<IMPvpWave> {
  constructor(private service: MPvpWaveService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpWave> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpWave>) => response.ok),
        map((mPvpWave: HttpResponse<MPvpWave>) => mPvpWave.body)
      );
    }
    return of(new MPvpWave());
  }
}

export const mPvpWaveRoute: Routes = [
  {
    path: '',
    component: MPvpWaveComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpWaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpWaveDetailComponent,
    resolve: {
      mPvpWave: MPvpWaveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpWaveUpdateComponent,
    resolve: {
      mPvpWave: MPvpWaveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpWaveUpdateComponent,
    resolve: {
      mPvpWave: MPvpWaveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWaves'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpWavePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpWaveDeletePopupComponent,
    resolve: {
      mPvpWave: MPvpWaveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWaves'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
