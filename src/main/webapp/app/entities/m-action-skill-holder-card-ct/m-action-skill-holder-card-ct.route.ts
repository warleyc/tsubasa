import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';
import { MActionSkillHolderCardCtService } from './m-action-skill-holder-card-ct.service';
import { MActionSkillHolderCardCtComponent } from './m-action-skill-holder-card-ct.component';
import { MActionSkillHolderCardCtDetailComponent } from './m-action-skill-holder-card-ct-detail.component';
import { MActionSkillHolderCardCtUpdateComponent } from './m-action-skill-holder-card-ct-update.component';
import { MActionSkillHolderCardCtDeletePopupComponent } from './m-action-skill-holder-card-ct-delete-dialog.component';
import { IMActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';

@Injectable({ providedIn: 'root' })
export class MActionSkillHolderCardCtResolve implements Resolve<IMActionSkillHolderCardCt> {
  constructor(private service: MActionSkillHolderCardCtService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMActionSkillHolderCardCt> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MActionSkillHolderCardCt>) => response.ok),
        map((mActionSkillHolderCardCt: HttpResponse<MActionSkillHolderCardCt>) => mActionSkillHolderCardCt.body)
      );
    }
    return of(new MActionSkillHolderCardCt());
  }
}

export const mActionSkillHolderCardCtRoute: Routes = [
  {
    path: '',
    component: MActionSkillHolderCardCtComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActionSkillHolderCardCts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionSkillHolderCardCtDetailComponent,
    resolve: {
      mActionSkillHolderCardCt: MActionSkillHolderCardCtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardCts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionSkillHolderCardCtUpdateComponent,
    resolve: {
      mActionSkillHolderCardCt: MActionSkillHolderCardCtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardCts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionSkillHolderCardCtUpdateComponent,
    resolve: {
      mActionSkillHolderCardCt: MActionSkillHolderCardCtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardCts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionSkillHolderCardCtPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionSkillHolderCardCtDeletePopupComponent,
    resolve: {
      mActionSkillHolderCardCt: MActionSkillHolderCardCtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardCts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
