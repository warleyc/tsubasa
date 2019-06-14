import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MNpcPersonality } from 'app/shared/model/m-npc-personality.model';
import { MNpcPersonalityService } from './m-npc-personality.service';
import { MNpcPersonalityComponent } from './m-npc-personality.component';
import { MNpcPersonalityDetailComponent } from './m-npc-personality-detail.component';
import { MNpcPersonalityUpdateComponent } from './m-npc-personality-update.component';
import { MNpcPersonalityDeletePopupComponent } from './m-npc-personality-delete-dialog.component';
import { IMNpcPersonality } from 'app/shared/model/m-npc-personality.model';

@Injectable({ providedIn: 'root' })
export class MNpcPersonalityResolve implements Resolve<IMNpcPersonality> {
  constructor(private service: MNpcPersonalityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMNpcPersonality> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MNpcPersonality>) => response.ok),
        map((mNpcPersonality: HttpResponse<MNpcPersonality>) => mNpcPersonality.body)
      );
    }
    return of(new MNpcPersonality());
  }
}

export const mNpcPersonalityRoute: Routes = [
  {
    path: '',
    component: MNpcPersonalityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MNpcPersonalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MNpcPersonalityDetailComponent,
    resolve: {
      mNpcPersonality: MNpcPersonalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcPersonalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MNpcPersonalityUpdateComponent,
    resolve: {
      mNpcPersonality: MNpcPersonalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcPersonalities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MNpcPersonalityUpdateComponent,
    resolve: {
      mNpcPersonality: MNpcPersonalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcPersonalities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mNpcPersonalityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MNpcPersonalityDeletePopupComponent,
    resolve: {
      mNpcPersonality: MNpcPersonalityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcPersonalities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
