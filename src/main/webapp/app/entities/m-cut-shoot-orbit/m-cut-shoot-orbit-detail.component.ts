import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';

@Component({
  selector: 'jhi-m-cut-shoot-orbit-detail',
  templateUrl: './m-cut-shoot-orbit-detail.component.html'
})
export class MCutShootOrbitDetailComponent implements OnInit {
  mCutShootOrbit: IMCutShootOrbit;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutShootOrbit }) => {
      this.mCutShootOrbit = mCutShootOrbit;
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
