import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MArousalMaterial } from 'app/shared/model/m-arousal-material.model';
import { MArousalMaterialService } from './m-arousal-material.service';
import { MArousalMaterialComponent } from './m-arousal-material.component';
import { MArousalMaterialDetailComponent } from './m-arousal-material-detail.component';
import { MArousalMaterialUpdateComponent } from './m-arousal-material-update.component';
import { MArousalMaterialDeletePopupComponent } from './m-arousal-material-delete-dialog.component';
import { IMArousalMaterial } from 'app/shared/model/m-arousal-material.model';

@Injectable({ providedIn: 'root' })
export class MArousalMaterialResolve implements Resolve<IMArousalMaterial> {
  constructor(private service: MArousalMaterialService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMArousalMaterial> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MArousalMaterial>) => response.ok),
        map((mArousalMaterial: HttpResponse<MArousalMaterial>) => mArousalMaterial.body)
      );
    }
    return of(new MArousalMaterial());
  }
}

export const mArousalMaterialRoute: Routes = [
  {
    path: '',
    component: MArousalMaterialComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MArousalMaterials'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MArousalMaterialDetailComponent,
    resolve: {
      mArousalMaterial: MArousalMaterialResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalMaterials'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MArousalMaterialUpdateComponent,
    resolve: {
      mArousalMaterial: MArousalMaterialResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalMaterials'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MArousalMaterialUpdateComponent,
    resolve: {
      mArousalMaterial: MArousalMaterialResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalMaterials'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mArousalMaterialPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MArousalMaterialDeletePopupComponent,
    resolve: {
      mArousalMaterial: MArousalMaterialResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalMaterials'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
