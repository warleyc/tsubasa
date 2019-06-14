/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MInheritActionSkillCostDetailComponent } from 'app/entities/m-inherit-action-skill-cost/m-inherit-action-skill-cost-detail.component';
import { MInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MInheritActionSkillCost Management Detail Component', () => {
    let comp: MInheritActionSkillCostDetailComponent;
    let fixture: ComponentFixture<MInheritActionSkillCostDetailComponent>;
    const route = ({ data: of({ mInheritActionSkillCost: new MInheritActionSkillCost(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInheritActionSkillCostDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MInheritActionSkillCostDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MInheritActionSkillCostDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mInheritActionSkillCost).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
