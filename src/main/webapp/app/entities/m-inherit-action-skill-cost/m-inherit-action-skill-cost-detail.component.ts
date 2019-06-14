import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';

@Component({
  selector: 'jhi-m-inherit-action-skill-cost-detail',
  templateUrl: './m-inherit-action-skill-cost-detail.component.html'
})
export class MInheritActionSkillCostDetailComponent implements OnInit {
  mInheritActionSkillCost: IMInheritActionSkillCost;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mInheritActionSkillCost }) => {
      this.mInheritActionSkillCost = mInheritActionSkillCost;
    });
  }

  previousState() {
    window.history.back();
  }
}
