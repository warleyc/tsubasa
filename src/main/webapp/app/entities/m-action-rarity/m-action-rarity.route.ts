import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MActionRarity } from 'app/shared/model/m-action-rarity.model';
import { MActionRarityService } from './m-action-rarity.service';
import { MActionRarityComponent } from './m-action-rarity.component';
import { MActionRarityDetailComponent } from './m-action-rarity-detail.component';
import { MActionRarityUpdateComponent } from './m-action-rarity-update.component';
import { MActionRarityDeletePopupComponent } from './m-action-rarity-delete-dialog.component';
import { IMActionRarity } from 'app/shared/model/m-action-rarity.model';

@Injectable({ providedIn: 'root' })
export class MActionRarityResolve implements Resolve<IMActionRarity> {
  constructor(private service: MActionRarityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMActionRarity> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MActionRarity>) => response.ok),
        map((mActionRarity: HttpResponse<MActionRarity>) => mActionRarity.body)
      );
    }
    return of(new MActionRarity());
  }
}

export const mActionRarityRoute: Routes = [
  {
    path: '',
    component: MActionRarityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActionRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionRarityDetailComponent,
    resolve: {
      mActionRarity: MActionRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionRarityUpdateComponent,
    resolve: {
      mActionRarity: MActionRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionRarityUpdateComponent,
    resolve: {
      mActionRarity: MActionRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionRarities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionRarityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionRarityDeletePopupComponent,
    resolve: {
      mActionRarity: MActionRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionRarities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
