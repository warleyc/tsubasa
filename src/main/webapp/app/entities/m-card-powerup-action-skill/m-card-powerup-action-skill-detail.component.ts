import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

@Component({
  selector: 'jhi-m-card-powerup-action-skill-detail',
  templateUrl: './m-card-powerup-action-skill-detail.component.html'
})
export class MCardPowerupActionSkillDetailComponent implements OnInit {
  mCardPowerupActionSkill: IMCardPowerupActionSkill;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardPowerupActionSkill }) => {
      this.mCardPowerupActionSkill = mCardPowerupActionSkill;
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
