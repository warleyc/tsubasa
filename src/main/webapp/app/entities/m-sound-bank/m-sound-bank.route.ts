import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSoundBank } from 'app/shared/model/m-sound-bank.model';
import { MSoundBankService } from './m-sound-bank.service';
import { MSoundBankComponent } from './m-sound-bank.component';
import { MSoundBankDetailComponent } from './m-sound-bank-detail.component';
import { MSoundBankUpdateComponent } from './m-sound-bank-update.component';
import { MSoundBankDeletePopupComponent } from './m-sound-bank-delete-dialog.component';
import { IMSoundBank } from 'app/shared/model/m-sound-bank.model';

@Injectable({ providedIn: 'root' })
export class MSoundBankResolve implements Resolve<IMSoundBank> {
  constructor(private service: MSoundBankService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSoundBank> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSoundBank>) => response.ok),
        map((mSoundBank: HttpResponse<MSoundBank>) => mSoundBank.body)
      );
    }
    return of(new MSoundBank());
  }
}

export const mSoundBankRoute: Routes = [
  {
    path: '',
    component: MSoundBankComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSoundBanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSoundBankDetailComponent,
    resolve: {
      mSoundBank: MSoundBankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSoundBankUpdateComponent,
    resolve: {
      mSoundBank: MSoundBankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBanks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSoundBankUpdateComponent,
    resolve: {
      mSoundBank: MSoundBankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBanks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSoundBankPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSoundBankDeletePopupComponent,
    resolve: {
      mSoundBank: MSoundBankResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBanks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
