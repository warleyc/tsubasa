import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';
import { MSoundBankEventService } from './m-sound-bank-event.service';
import { MSoundBankEventComponent } from './m-sound-bank-event.component';
import { MSoundBankEventDetailComponent } from './m-sound-bank-event-detail.component';
import { MSoundBankEventUpdateComponent } from './m-sound-bank-event-update.component';
import { MSoundBankEventDeletePopupComponent } from './m-sound-bank-event-delete-dialog.component';
import { IMSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';

@Injectable({ providedIn: 'root' })
export class MSoundBankEventResolve implements Resolve<IMSoundBankEvent> {
  constructor(private service: MSoundBankEventService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSoundBankEvent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSoundBankEvent>) => response.ok),
        map((mSoundBankEvent: HttpResponse<MSoundBankEvent>) => mSoundBankEvent.body)
      );
    }
    return of(new MSoundBankEvent());
  }
}

export const mSoundBankEventRoute: Routes = [
  {
    path: '',
    component: MSoundBankEventComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSoundBankEvents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSoundBankEventDetailComponent,
    resolve: {
      mSoundBankEvent: MSoundBankEventResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBankEvents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSoundBankEventUpdateComponent,
    resolve: {
      mSoundBankEvent: MSoundBankEventResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBankEvents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSoundBankEventUpdateComponent,
    resolve: {
      mSoundBankEvent: MSoundBankEventResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBankEvents'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSoundBankEventPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSoundBankEventDeletePopupComponent,
    resolve: {
      mSoundBankEvent: MSoundBankEventResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSoundBankEvents'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
