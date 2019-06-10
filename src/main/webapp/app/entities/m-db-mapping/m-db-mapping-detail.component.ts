import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDbMapping } from 'app/shared/model/m-db-mapping.model';

@Component({
  selector: 'jhi-m-db-mapping-detail',
  templateUrl: './m-db-mapping-detail.component.html'
})
export class MDbMappingDetailComponent implements OnInit {
  mDbMapping: IMDbMapping;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDbMapping }) => {
      this.mDbMapping = mDbMapping;
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
