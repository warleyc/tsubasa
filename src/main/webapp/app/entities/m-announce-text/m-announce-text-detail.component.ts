import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAnnounceText } from 'app/shared/model/m-announce-text.model';

@Component({
  selector: 'jhi-m-announce-text-detail',
  templateUrl: './m-announce-text-detail.component.html'
})
export class MAnnounceTextDetailComponent implements OnInit {
  mAnnounceText: IMAnnounceText;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAnnounceText }) => {
      this.mAnnounceText = mAnnounceText;
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
