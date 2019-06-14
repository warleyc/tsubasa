import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';
import { MTeamEffectRarityService } from './m-team-effect-rarity.service';
import { MTeamEffectRarityComponent } from './m-team-effect-rarity.component';
import { MTeamEffectRarityDetailComponent } from './m-team-effect-rarity-detail.component';
import { MTeamEffectRarityUpdateComponent } from './m-team-effect-rarity-update.component';
import { MTeamEffectRarityDeletePopupComponent } from './m-team-effect-rarity-delete-dialog.component';
import { IMTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';

@Injectable({ providedIn: 'root' })
export class MTeamEffectRarityResolve implements Resolve<IMTeamEffectRarity> {
  constructor(private service: MTeamEffectRarityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTeamEffectRarity> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTeamEffectRarity>) => response.ok),
        map((mTeamEffectRarity: HttpResponse<MTeamEffectRarity>) => mTeamEffectRarity.body)
      );
    }
    return of(new MTeamEffectRarity());
  }
}

export const mTeamEffectRarityRoute: Routes = [
  {
    path: '',
    component: MTeamEffectRarityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTeamEffectRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTeamEffectRarityDetailComponent,
    resolve: {
      mTeamEffectRarity: MTeamEffectRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTeamEffectRarityUpdateComponent,
    resolve: {
      mTeamEffectRarity: MTeamEffectRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectRarities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTeamEffectRarityUpdateComponent,
    resolve: {
      mTeamEffectRarity: MTeamEffectRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectRarities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTeamEffectRarityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTeamEffectRarityDeletePopupComponent,
    resolve: {
      mTeamEffectRarity: MTeamEffectRarityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectRarities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
