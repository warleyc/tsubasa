import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';
import { MTeamEffectBaseService } from './m-team-effect-base.service';
import { MTeamEffectBaseComponent } from './m-team-effect-base.component';
import { MTeamEffectBaseDetailComponent } from './m-team-effect-base-detail.component';
import { MTeamEffectBaseUpdateComponent } from './m-team-effect-base-update.component';
import { MTeamEffectBaseDeletePopupComponent } from './m-team-effect-base-delete-dialog.component';
import { IMTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

@Injectable({ providedIn: 'root' })
export class MTeamEffectBaseResolve implements Resolve<IMTeamEffectBase> {
  constructor(private service: MTeamEffectBaseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTeamEffectBase> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTeamEffectBase>) => response.ok),
        map((mTeamEffectBase: HttpResponse<MTeamEffectBase>) => mTeamEffectBase.body)
      );
    }
    return of(new MTeamEffectBase());
  }
}

export const mTeamEffectBaseRoute: Routes = [
  {
    path: '',
    component: MTeamEffectBaseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTeamEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTeamEffectBaseDetailComponent,
    resolve: {
      mTeamEffectBase: MTeamEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTeamEffectBaseUpdateComponent,
    resolve: {
      mTeamEffectBase: MTeamEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTeamEffectBaseUpdateComponent,
    resolve: {
      mTeamEffectBase: MTeamEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectBases'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTeamEffectBasePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTeamEffectBaseDeletePopupComponent,
    resolve: {
      mTeamEffectBase: MTeamEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectBases'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
