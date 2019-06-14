import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';

@Component({
  selector: 'jhi-m-powerup-action-skill-cost-detail',
  templateUrl: './m-powerup-action-skill-cost-detail.component.html'
})
export class MPowerupActionSkillCostDetailComponent implements OnInit {
  mPowerupActionSkillCost: IMPowerupActionSkillCost;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPowerupActionSkillCost }) => {
      this.mPowerupActionSkillCost = mPowerupActionSkillCost;
    });
  }

  previousState() {
    window.history.back();
  }
}
