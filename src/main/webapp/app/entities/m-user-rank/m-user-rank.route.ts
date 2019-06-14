import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MUserRank } from 'app/shared/model/m-user-rank.model';
import { MUserRankService } from './m-user-rank.service';
import { MUserRankComponent } from './m-user-rank.component';
import { MUserRankDetailComponent } from './m-user-rank-detail.component';
import { MUserRankUpdateComponent } from './m-user-rank-update.component';
import { MUserRankDeletePopupComponent } from './m-user-rank-delete-dialog.component';
import { IMUserRank } from 'app/shared/model/m-user-rank.model';

@Injectable({ providedIn: 'root' })
export class MUserRankResolve implements Resolve<IMUserRank> {
  constructor(private service: MUserRankService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMUserRank> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MUserRank>) => response.ok),
        map((mUserRank: HttpResponse<MUserRank>) => mUserRank.body)
      );
    }
    return of(new MUserRank());
  }
}

export const mUserRankRoute: Routes = [
  {
    path: '',
    component: MUserRankComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MUserRanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MUserRankDetailComponent,
    resolve: {
      mUserRank: MUserRankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserRanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MUserRankUpdateComponent,
    resolve: {
      mUserRank: MUserRankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserRanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MUserRankUpdateComponent,
    resolve: {
      mUserRank: MUserRankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserRanks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mUserRankPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MUserRankDeletePopupComponent,
    resolve: {
      mUserRank: MUserRankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserRanks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
