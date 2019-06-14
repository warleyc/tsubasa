import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';
import { MTargetCharacterGroupService } from './m-target-character-group.service';
import { MTargetCharacterGroupComponent } from './m-target-character-group.component';
import { MTargetCharacterGroupDetailComponent } from './m-target-character-group-detail.component';
import { MTargetCharacterGroupUpdateComponent } from './m-target-character-group-update.component';
import { MTargetCharacterGroupDeletePopupComponent } from './m-target-character-group-delete-dialog.component';
import { IMTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetCharacterGroupResolve implements Resolve<IMTargetCharacterGroup> {
  constructor(private service: MTargetCharacterGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetCharacterGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetCharacterGroup>) => response.ok),
        map((mTargetCharacterGroup: HttpResponse<MTargetCharacterGroup>) => mTargetCharacterGroup.body)
      );
    }
    return of(new MTargetCharacterGroup());
  }
}

export const mTargetCharacterGroupRoute: Routes = [
  {
    path: '',
    component: MTargetCharacterGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetCharacterGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetCharacterGroupDetailComponent,
    resolve: {
      mTargetCharacterGroup: MTargetCharacterGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetCharacterGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetCharacterGroupUpdateComponent,
    resolve: {
      mTargetCharacterGroup: MTargetCharacterGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetCharacterGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetCharacterGroupUpdateComponent,
    resolve: {
      mTargetCharacterGroup: MTargetCharacterGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetCharacterGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetCharacterGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetCharacterGroupDeletePopupComponent,
    resolve: {
      mTargetCharacterGroup: MTargetCharacterGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetCharacterGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
