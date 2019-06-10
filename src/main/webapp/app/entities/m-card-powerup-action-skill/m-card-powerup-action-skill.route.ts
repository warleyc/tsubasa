import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';
import { MCardPowerupActionSkillService } from './m-card-powerup-action-skill.service';
import { MCardPowerupActionSkillComponent } from './m-card-powerup-action-skill.component';
import { MCardPowerupActionSkillDetailComponent } from './m-card-powerup-action-skill-detail.component';
import { MCardPowerupActionSkillUpdateComponent } from './m-card-powerup-action-skill-update.component';
import { MCardPowerupActionSkillDeletePopupComponent } from './m-card-powerup-action-skill-delete-dialog.component';
import { IMCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

@Injectable({ providedIn: 'root' })
export class MCardPowerupActionSkillResolve implements Resolve<IMCardPowerupActionSkill> {
  constructor(private service: MCardPowerupActionSkillService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardPowerupActionSkill> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardPowerupActionSkill>) => response.ok),
        map((mCardPowerupActionSkill: HttpResponse<MCardPowerupActionSkill>) => mCardPowerupActionSkill.body)
      );
    }
    return of(new MCardPowerupActionSkill());
  }
}

export const mCardPowerupActionSkillRoute: Routes = [
  {
    path: '',
    component: MCardPowerupActionSkillComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardPowerupActionSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardPowerupActionSkillDetailComponent,
    resolve: {
      mCardPowerupActionSkill: MCardPowerupActionSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPowerupActionSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardPowerupActionSkillUpdateComponent,
    resolve: {
      mCardPowerupActionSkill: MCardPowerupActionSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPowerupActionSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardPowerupActionSkillUpdateComponent,
    resolve: {
      mCardPowerupActionSkill: MCardPowerupActionSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPowerupActionSkills'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardPowerupActionSkillPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardPowerupActionSkillDeletePopupComponent,
    resolve: {
      mCardPowerupActionSkill: MCardPowerupActionSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPowerupActionSkills'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
