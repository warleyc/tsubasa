import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMExtraNews } from 'app/shared/model/m-extra-news.model';

@Component({
  selector: 'jhi-m-extra-news-detail',
  templateUrl: './m-extra-news-detail.component.html'
})
export class MExtraNewsDetailComponent implements OnInit {
  mExtraNews: IMExtraNews;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mExtraNews }) => {
      this.mExtraNews = mExtraNews;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
