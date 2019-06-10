/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDetachActionSkillCostDetailComponent } from 'app/entities/m-detach-action-skill-cost/m-detach-action-skill-cost-detail.component';
import { MDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MDetachActionSkillCost Management Detail Component', () => {
    let comp: MDetachActionSkillCostDetailComponent;
    let fixture: ComponentFixture<MDetachActionSkillCostDetailComponent>;
    const route = ({ data: of({ mDetachActionSkillCost: new MDetachActionSkillCost(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDetachActionSkillCostDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDetachActionSkillCostDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDetachActionSkillCostDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDetachActionSkillCost).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
