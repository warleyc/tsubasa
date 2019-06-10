import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MFormation } from 'app/shared/model/m-formation.model';
import { MFormationService } from './m-formation.service';
import { MFormationComponent } from './m-formation.component';
import { MFormationDetailComponent } from './m-formation-detail.component';
import { MFormationUpdateComponent } from './m-formation-update.component';
import { MFormationDeletePopupComponent } from './m-formation-delete-dialog.component';
import { IMFormation } from 'app/shared/model/m-formation.model';

@Injectable({ providedIn: 'root' })
export class MFormationResolve implements Resolve<IMFormation> {
  constructor(private service: MFormationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMFormation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MFormation>) => response.ok),
        map((mFormation: HttpResponse<MFormation>) => mFormation.body)
      );
    }
    return of(new MFormation());
  }
}

export const mFormationRoute: Routes = [
  {
    path: '',
    component: MFormationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MFormationDetailComponent,
    resolve: {
      mFormation: MFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MFormationUpdateComponent,
    resolve: {
      mFormation: MFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MFormationUpdateComponent,
    resolve: {
      mFormation: MFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFormations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mFormationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MFormationDeletePopupComponent,
    resolve: {
      mFormation: MFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MFormations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
