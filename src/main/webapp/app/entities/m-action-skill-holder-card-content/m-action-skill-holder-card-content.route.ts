import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';
import { MActionSkillHolderCardContentService } from './m-action-skill-holder-card-content.service';
import { MActionSkillHolderCardContentComponent } from './m-action-skill-holder-card-content.component';
import { MActionSkillHolderCardContentDetailComponent } from './m-action-skill-holder-card-content-detail.component';
import { MActionSkillHolderCardContentUpdateComponent } from './m-action-skill-holder-card-content-update.component';
import { MActionSkillHolderCardContentDeletePopupComponent } from './m-action-skill-holder-card-content-delete-dialog.component';
import { IMActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';

@Injectable({ providedIn: 'root' })
export class MActionSkillHolderCardContentResolve implements Resolve<IMActionSkillHolderCardContent> {
  constructor(private service: MActionSkillHolderCardContentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMActionSkillHolderCardContent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MActionSkillHolderCardContent>) => response.ok),
        map((mActionSkillHolderCardContent: HttpResponse<MActionSkillHolderCardContent>) => mActionSkillHolderCardContent.body)
      );
    }
    return of(new MActionSkillHolderCardContent());
  }
}

export const mActionSkillHolderCardContentRoute: Routes = [
  {
    path: '',
    component: MActionSkillHolderCardContentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActionSkillHolderCardContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionSkillHolderCardContentDetailComponent,
    resolve: {
      mActionSkillHolderCardContent: MActionSkillHolderCardContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionSkillHolderCardContentUpdateComponent,
    resolve: {
      mActionSkillHolderCardContent: MActionSkillHolderCardContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionSkillHolderCardContentUpdateComponent,
    resolve: {
      mActionSkillHolderCardContent: MActionSkillHolderCardContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardContents'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionSkillHolderCardContentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionSkillHolderCardContentDeletePopupComponent,
    resolve: {
      mActionSkillHolderCardContent: MActionSkillHolderCardContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionSkillHolderCardContents'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
