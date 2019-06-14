/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPowerupActionSkillCostDetailComponent } from 'app/entities/m-powerup-action-skill-cost/m-powerup-action-skill-cost-detail.component';
import { MPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MPowerupActionSkillCost Management Detail Component', () => {
    let comp: MPowerupActionSkillCostDetailComponent;
    let fixture: ComponentFixture<MPowerupActionSkillCostDetailComponent>;
    const route = ({ data: of({ mPowerupActionSkillCost: new MPowerupActionSkillCost(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPowerupActionSkillCostDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPowerupActionSkillCostDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPowerupActionSkillCostDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPowerupActionSkillCost).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
