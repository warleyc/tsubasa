import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMHomeMarquee } from 'app/shared/model/m-home-marquee.model';

@Component({
  selector: 'jhi-m-home-marquee-detail',
  templateUrl: './m-home-marquee-detail.component.html'
})
export class MHomeMarqueeDetailComponent implements OnInit {
  mHomeMarquee: IMHomeMarquee;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mHomeMarquee }) => {
      this.mHomeMarquee = mHomeMarquee;
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
