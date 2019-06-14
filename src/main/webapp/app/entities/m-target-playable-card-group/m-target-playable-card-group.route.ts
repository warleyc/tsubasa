import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';
import { MTargetPlayableCardGroupService } from './m-target-playable-card-group.service';
import { MTargetPlayableCardGroupComponent } from './m-target-playable-card-group.component';
import { MTargetPlayableCardGroupDetailComponent } from './m-target-playable-card-group-detail.component';
import { MTargetPlayableCardGroupUpdateComponent } from './m-target-playable-card-group-update.component';
import { MTargetPlayableCardGroupDeletePopupComponent } from './m-target-playable-card-group-delete-dialog.component';
import { IMTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetPlayableCardGroupResolve implements Resolve<IMTargetPlayableCardGroup> {
  constructor(private service: MTargetPlayableCardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetPlayableCardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetPlayableCardGroup>) => response.ok),
        map((mTargetPlayableCardGroup: HttpResponse<MTargetPlayableCardGroup>) => mTargetPlayableCardGroup.body)
      );
    }
    return of(new MTargetPlayableCardGroup());
  }
}

export const mTargetPlayableCardGroupRoute: Routes = [
  {
    path: '',
    component: MTargetPlayableCardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetPlayableCardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetPlayableCardGroupDetailComponent,
    resolve: {
      mTargetPlayableCardGroup: MTargetPlayableCardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetPlayableCardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetPlayableCardGroupUpdateComponent,
    resolve: {
      mTargetPlayableCardGroup: MTargetPlayableCardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetPlayableCardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetPlayableCardGroupUpdateComponent,
    resolve: {
      mTargetPlayableCardGroup: MTargetPlayableCardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetPlayableCardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetPlayableCardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetPlayableCardGroupDeletePopupComponent,
    resolve: {
      mTargetPlayableCardGroup: MTargetPlayableCardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetPlayableCardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
