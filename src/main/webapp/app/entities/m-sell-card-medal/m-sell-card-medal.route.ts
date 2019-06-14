import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';
import { MSellCardMedalService } from './m-sell-card-medal.service';
import { MSellCardMedalComponent } from './m-sell-card-medal.component';
import { MSellCardMedalDetailComponent } from './m-sell-card-medal-detail.component';
import { MSellCardMedalUpdateComponent } from './m-sell-card-medal-update.component';
import { MSellCardMedalDeletePopupComponent } from './m-sell-card-medal-delete-dialog.component';
import { IMSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';

@Injectable({ providedIn: 'root' })
export class MSellCardMedalResolve implements Resolve<IMSellCardMedal> {
  constructor(private service: MSellCardMedalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSellCardMedal> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSellCardMedal>) => response.ok),
        map((mSellCardMedal: HttpResponse<MSellCardMedal>) => mSellCardMedal.body)
      );
    }
    return of(new MSellCardMedal());
  }
}

export const mSellCardMedalRoute: Routes = [
  {
    path: '',
    component: MSellCardMedalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSellCardMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSellCardMedalDetailComponent,
    resolve: {
      mSellCardMedal: MSellCardMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSellCardMedalUpdateComponent,
    resolve: {
      mSellCardMedal: MSellCardMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardMedals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSellCardMedalUpdateComponent,
    resolve: {
      mSellCardMedal: MSellCardMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardMedals'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSellCardMedalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSellCardMedalDeletePopupComponent,
    resolve: {
      mSellCardMedal: MSellCardMedalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardMedals'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
