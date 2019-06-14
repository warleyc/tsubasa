import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMatchFormation } from 'app/shared/model/m-match-formation.model';
import { MMatchFormationService } from './m-match-formation.service';
import { MMatchFormationComponent } from './m-match-formation.component';
import { MMatchFormationDetailComponent } from './m-match-formation-detail.component';
import { MMatchFormationUpdateComponent } from './m-match-formation-update.component';
import { MMatchFormationDeletePopupComponent } from './m-match-formation-delete-dialog.component';
import { IMMatchFormation } from 'app/shared/model/m-match-formation.model';

@Injectable({ providedIn: 'root' })
export class MMatchFormationResolve implements Resolve<IMMatchFormation> {
  constructor(private service: MMatchFormationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMatchFormation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMatchFormation>) => response.ok),
        map((mMatchFormation: HttpResponse<MMatchFormation>) => mMatchFormation.body)
      );
    }
    return of(new MMatchFormation());
  }
}

export const mMatchFormationRoute: Routes = [
  {
    path: '',
    component: MMatchFormationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMatchFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMatchFormationDetailComponent,
    resolve: {
      mMatchFormation: MMatchFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMatchFormationUpdateComponent,
    resolve: {
      mMatchFormation: MMatchFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchFormations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMatchFormationUpdateComponent,
    resolve: {
      mMatchFormation: MMatchFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchFormations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMatchFormationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMatchFormationDeletePopupComponent,
    resolve: {
      mMatchFormation: MMatchFormationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchFormations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
