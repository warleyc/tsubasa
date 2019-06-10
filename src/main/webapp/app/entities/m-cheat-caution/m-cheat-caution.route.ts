import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCheatCaution } from 'app/shared/model/m-cheat-caution.model';
import { MCheatCautionService } from './m-cheat-caution.service';
import { MCheatCautionComponent } from './m-cheat-caution.component';
import { MCheatCautionDetailComponent } from './m-cheat-caution-detail.component';
import { MCheatCautionUpdateComponent } from './m-cheat-caution-update.component';
import { MCheatCautionDeletePopupComponent } from './m-cheat-caution-delete-dialog.component';
import { IMCheatCaution } from 'app/shared/model/m-cheat-caution.model';

@Injectable({ providedIn: 'root' })
export class MCheatCautionResolve implements Resolve<IMCheatCaution> {
  constructor(private service: MCheatCautionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCheatCaution> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCheatCaution>) => response.ok),
        map((mCheatCaution: HttpResponse<MCheatCaution>) => mCheatCaution.body)
      );
    }
    return of(new MCheatCaution());
  }
}

export const mCheatCautionRoute: Routes = [
  {
    path: '',
    component: MCheatCautionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCheatCautions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCheatCautionDetailComponent,
    resolve: {
      mCheatCaution: MCheatCautionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCheatCautions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCheatCautionUpdateComponent,
    resolve: {
      mCheatCaution: MCheatCautionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCheatCautions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCheatCautionUpdateComponent,
    resolve: {
      mCheatCaution: MCheatCautionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCheatCautions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCheatCautionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCheatCautionDeletePopupComponent,
    resolve: {
      mCheatCaution: MCheatCautionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCheatCautions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
