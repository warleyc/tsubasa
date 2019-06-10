import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';
import { MEncountersCommandCompatibilityService } from './m-encounters-command-compatibility.service';
import { MEncountersCommandCompatibilityComponent } from './m-encounters-command-compatibility.component';
import { MEncountersCommandCompatibilityDetailComponent } from './m-encounters-command-compatibility-detail.component';
import { MEncountersCommandCompatibilityUpdateComponent } from './m-encounters-command-compatibility-update.component';
import { MEncountersCommandCompatibilityDeletePopupComponent } from './m-encounters-command-compatibility-delete-dialog.component';
import { IMEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';

@Injectable({ providedIn: 'root' })
export class MEncountersCommandCompatibilityResolve implements Resolve<IMEncountersCommandCompatibility> {
  constructor(private service: MEncountersCommandCompatibilityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEncountersCommandCompatibility> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEncountersCommandCompatibility>) => response.ok),
        map((mEncountersCommandCompatibility: HttpResponse<MEncountersCommandCompatibility>) => mEncountersCommandCompatibility.body)
      );
    }
    return of(new MEncountersCommandCompatibility());
  }
}

export const mEncountersCommandCompatibilityRoute: Routes = [
  {
    path: '',
    component: MEncountersCommandCompatibilityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEncountersCommandCompatibilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEncountersCommandCompatibilityDetailComponent,
    resolve: {
      mEncountersCommandCompatibility: MEncountersCommandCompatibilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandCompatibilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEncountersCommandCompatibilityUpdateComponent,
    resolve: {
      mEncountersCommandCompatibility: MEncountersCommandCompatibilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandCompatibilities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEncountersCommandCompatibilityUpdateComponent,
    resolve: {
      mEncountersCommandCompatibility: MEncountersCommandCompatibilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandCompatibilities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEncountersCommandCompatibilityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEncountersCommandCompatibilityDeletePopupComponent,
    resolve: {
      mEncountersCommandCompatibility: MEncountersCommandCompatibilityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandCompatibilities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
