import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

@Component({
  selector: 'jhi-m-action-skill-cutin-detail',
  templateUrl: './m-action-skill-cutin-detail.component.html'
})
export class MActionSkillCutinDetailComponent implements OnInit {
  mActionSkillCutin: IMActionSkillCutin;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillCutin }) => {
      this.mActionSkillCutin = mActionSkillCutin;
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
