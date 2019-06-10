import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';
import { MCharacterScoreCutService } from './m-character-score-cut.service';
import { MCharacterScoreCutComponent } from './m-character-score-cut.component';
import { MCharacterScoreCutDetailComponent } from './m-character-score-cut-detail.component';
import { MCharacterScoreCutUpdateComponent } from './m-character-score-cut-update.component';
import { MCharacterScoreCutDeletePopupComponent } from './m-character-score-cut-delete-dialog.component';
import { IMCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';

@Injectable({ providedIn: 'root' })
export class MCharacterScoreCutResolve implements Resolve<IMCharacterScoreCut> {
  constructor(private service: MCharacterScoreCutService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCharacterScoreCut> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCharacterScoreCut>) => response.ok),
        map((mCharacterScoreCut: HttpResponse<MCharacterScoreCut>) => mCharacterScoreCut.body)
      );
    }
    return of(new MCharacterScoreCut());
  }
}

export const mCharacterScoreCutRoute: Routes = [
  {
    path: '',
    component: MCharacterScoreCutComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCharacterScoreCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCharacterScoreCutDetailComponent,
    resolve: {
      mCharacterScoreCut: MCharacterScoreCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterScoreCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCharacterScoreCutUpdateComponent,
    resolve: {
      mCharacterScoreCut: MCharacterScoreCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterScoreCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCharacterScoreCutUpdateComponent,
    resolve: {
      mCharacterScoreCut: MCharacterScoreCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterScoreCuts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCharacterScoreCutPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCharacterScoreCutDeletePopupComponent,
    resolve: {
      mCharacterScoreCut: MCharacterScoreCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterScoreCuts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
