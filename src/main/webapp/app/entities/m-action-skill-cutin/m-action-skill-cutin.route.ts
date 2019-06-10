import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';
import { MActionSkillCutinService } from './m-action-skill-cutin.service';
import { MActionSkillCutinComponent } from './m-action-skill-cutin.component';
import { MActionSkillCutinDetailComponent } from './m-action-skill-cutin-detail.component';
import { MActionSkillCutinUpdateComponent } from './m-action-skill-cutin-update.component';
import { MActionSkillCutinDeletePopupComponent } from './m-action-skill-cutin-delete-dialog.component';
import { IMActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

@Injectable({ providedIn: 'root' })
export class MActionSkillCutinResolve implements Resolve<IMActionSkillCutin> {
  constructor(private service: MActionSkillCutinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMActionSkillCutin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MActionSkillCutin>) => response.ok),
        map((mActionSkillCutin: HttpResponse<MActionSkillCutin>) => mActionSkillCutin.body)
      );
    }
    return of(new MActionSkillCutin());
  }
}

export const mActionSkillCutinRoute: Routes = [
  {
    path: '',
    component: MActionSkillCutinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActionSkillCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionSkillCutinDetailComponent,
    resolve: {
      mActionSkillCutin: MActionSkillCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionSkillCutinUpdateComponent,
    resolve: {
      mActionSkillCutin: MActionSkillCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionSkillCutinUpdateComponent,
    resolve: {
      mActionSkillCutin: MActionSkillCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillCutins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionSkillCutinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionSkillCutinDeletePopupComponent,
    resolve: {
      mActionSkillCutin: MActionSkillCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillCutins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
