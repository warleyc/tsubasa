import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardRarity } from 'app/shared/model/m-card-rarity.model';
import { MCardRarityService } from './m-card-rarity.service';
import { MCardRarityComponent } from './m-card-rarity.component';
import { MCardRarityDetailComponent } from './m-card-rarity-detail.component';
import { MCardRarityUpdateComponent } from './m-card-rarity-update.component';
import { MCardRarityDeletePopupComponent } from './m-card-rarity-delete-dialog.component';
import { IMCardRarity } from 'app/shared/model/m-card-rarity.model';

@Injectable({ providedIn: 'root' })
export class MCardRarityResolve implements Resolve<IMCardRarity> {
  constructor(private service: MCardRarityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardRarity> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardRarity>) => response.ok),
        map((mCardRarity: HttpResponse<MCardRarity>) => mCardRarity.body)
      );
    }
    return of(new MCardRarity());
  }
}

export const mCardRarityRoute: Routes = [
  {
    path: '',
    component: MCardRarityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardRarityDetailComponent,
    resolve: {
      mCardRarity: MCardRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardRarityUpdateComponent,
    resolve: {
      mCardRarity: MCardRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardRarityUpdateComponent,
    resolve: {
      mCardRarity: MCardRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardRarities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardRarityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardRarityDeletePopupComponent,
    resolve: {
      mCardRarity: MCardRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardRarities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
