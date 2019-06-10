import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAchievement } from 'app/shared/model/m-achievement.model';
import { MAchievementService } from './m-achievement.service';
import { MAchievementComponent } from './m-achievement.component';
import { MAchievementDetailComponent } from './m-achievement-detail.component';
import { MAchievementUpdateComponent } from './m-achievement-update.component';
import { MAchievementDeletePopupComponent } from './m-achievement-delete-dialog.component';
import { IMAchievement } from 'app/shared/model/m-achievement.model';

@Injectable({ providedIn: 'root' })
export class MAchievementResolve implements Resolve<IMAchievement> {
  constructor(private service: MAchievementService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAchievement> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAchievement>) => response.ok),
        map((mAchievement: HttpResponse<MAchievement>) => mAchievement.body)
      );
    }
    return of(new MAchievement());
  }
}

export const mAchievementRoute: Routes = [
  {
    path: '',
    component: MAchievementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAchievements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAchievementDetailComponent,
    resolve: {
      mAchievement: MAchievementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAchievements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAchievementUpdateComponent,
    resolve: {
      mAchievement: MAchievementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAchievements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAchievementUpdateComponent,
    resolve: {
      mAchievement: MAchievementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAchievements'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAchievementPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAchievementDeletePopupComponent,
    resolve: {
      mAchievement: MAchievementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAchievements'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
