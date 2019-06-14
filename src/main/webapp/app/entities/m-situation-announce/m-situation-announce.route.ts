import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSituationAnnounce } from 'app/shared/model/m-situation-announce.model';
import { MSituationAnnounceService } from './m-situation-announce.service';
import { MSituationAnnounceComponent } from './m-situation-announce.component';
import { MSituationAnnounceDetailComponent } from './m-situation-announce-detail.component';
import { MSituationAnnounceUpdateComponent } from './m-situation-announce-update.component';
import { MSituationAnnounceDeletePopupComponent } from './m-situation-announce-delete-dialog.component';
import { IMSituationAnnounce } from 'app/shared/model/m-situation-announce.model';

@Injectable({ providedIn: 'root' })
export class MSituationAnnounceResolve implements Resolve<IMSituationAnnounce> {
  constructor(private service: MSituationAnnounceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSituationAnnounce> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSituationAnnounce>) => response.ok),
        map((mSituationAnnounce: HttpResponse<MSituationAnnounce>) => mSituationAnnounce.body)
      );
    }
    return of(new MSituationAnnounce());
  }
}

export const mSituationAnnounceRoute: Routes = [
  {
    path: '',
    component: MSituationAnnounceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSituationAnnounces'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSituationAnnounceDetailComponent,
    resolve: {
      mSituationAnnounce: MSituationAnnounceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituationAnnounces'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSituationAnnounceUpdateComponent,
    resolve: {
      mSituationAnnounce: MSituationAnnounceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituationAnnounces'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSituationAnnounceUpdateComponent,
    resolve: {
      mSituationAnnounce: MSituationAnnounceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituationAnnounces'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSituationAnnouncePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSituationAnnounceDeletePopupComponent,
    resolve: {
      mSituationAnnounce: MSituationAnnounceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituationAnnounces'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
