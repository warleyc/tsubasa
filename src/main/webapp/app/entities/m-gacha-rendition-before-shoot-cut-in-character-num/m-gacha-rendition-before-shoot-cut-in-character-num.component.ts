import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IMGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from './m-gacha-rendition-before-shoot-cut-in-character-num.service';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-character-num',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-character-num.component.html'
})
export class MGachaRenditionBeforeShootCutInCharacterNumComponent implements OnInit, OnDestroy {
  currentAccount: any;
  mGachaRenditionBeforeShootCutInCharacterNums: IMGachaRenditionBeforeShootCutInCharacterNum[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected mGachaRenditionBeforeShootCutInCharacterNumService: MGachaRenditionBeforeShootCutInCharacterNumService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.mGachaRenditionBeforeShootCutInCharacterNumService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IMGachaRenditionBeforeShootCutInCharacterNum[]>) =>
          this.paginateMGachaRenditionBeforeShootCutInCharacterNums(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/m-gacha-rendition-before-shoot-cut-in-character-num'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/m-gacha-rendition-before-shoot-cut-in-character-num',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMGachaRenditionBeforeShootCutInCharacterNums();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMGachaRenditionBeforeShootCutInCharacterNum) {
    return item.id;
  }

  registerChangeInMGachaRenditionBeforeShootCutInCharacterNums() {
    this.eventSubscriber = this.eventManager.subscribe('mGachaRenditionBeforeShootCutInCharacterNumListModification', response =>
      this.loadAll()
    );
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMGachaRenditionBeforeShootCutInCharacterNums(
    data: IMGachaRenditionBeforeShootCutInCharacterNum[],
    headers: HttpHeaders
  ) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.mGachaRenditionBeforeShootCutInCharacterNums = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
